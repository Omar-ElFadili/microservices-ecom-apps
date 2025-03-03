package org.sid.product.services;

import lombok.AllArgsConstructor;
import org.sid.product.ProductRepository;
import org.sid.product.dtos.ProductRequestDto;
import org.sid.product.dtos.ProductResponseDto;
import org.sid.product.dtos.purchase.ProductPurchaseRequestDto;
import org.sid.product.dtos.purchase.ProductPurchaseResponseDto;
import org.sid.product.entities.Product;
import org.sid.product.exceptions.ProductNotFoundException;
import org.sid.product.exceptions.ProductPurchaseException;
import org.sid.product.mappers.ProductRequestMapper;
import org.sid.product.mappers.ProductResponseMapper;
import org.sid.product.mappers.ProductPurchaseMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductRequestMapper productRequestMapper;
    private final ProductResponseMapper productResponseMapper;
    private final ProductPurchaseMapper productPurchaseMapper;

    public ProductService(ProductRepository productRepository, ProductRequestMapper productRequestMapper, ProductResponseMapper productResponseMapper, ProductPurchaseMapper productPurchaseMapper) {
        this.productRepository = productRepository;
        this.productRequestMapper = productRequestMapper;
        this.productResponseMapper = productResponseMapper;
        this.productPurchaseMapper = productPurchaseMapper;
    }

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = productRequestMapper.toEntity(productRequestDto);
        ProductResponseDto productResponseDto =  productResponseMapper.toDTO(productRepository.save(product));
        return productResponseDto;
    }

    public List<ProductPurchaseResponseDto> purchaseProducts(List<ProductPurchaseRequestDto> productRequestDtos) {
        List<Integer> idList = productRequestDtos
                .stream()
                .map(ProductPurchaseRequestDto::getProductId)
                .toList();

        List<Product> existedProductList = productRepository.findAllById(idList);
        if(existedProductList.size() != productRequestDtos.size()) {
            throw new ProductPurchaseException("one or more products don't exists");
        }

        List<ProductPurchaseRequestDto> storedRequestList = productRequestDtos
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequestDto::getProductId))
                .toList();
        List<ProductPurchaseResponseDto> purchasedProducts = new ArrayList<>();

        for(int i=0; i<storedRequestList.size(); i++) {
            Product product = existedProductList.get(i);
            ProductPurchaseRequestDto storedRequestDto = storedRequestList.get(i);
            if(product.getAvailableQuantity() < storedRequestDto.getQuantity()){
                throw new ProductPurchaseException("product insufficient in stock, is too small");
            }

            double availableQuantity = product.getAvailableQuantity() - storedRequestDto.getQuantity();
            product.setAvailableQuantity(availableQuantity);
            productRepository.save(product);

            ProductPurchaseResponseDto productPurchaseResponseDto = productPurchaseMapper.toRequest(storedRequestDto);
            purchasedProducts.add(productPurchaseResponseDto);
        }
        return purchasedProducts;
    }

    public ProductResponseDto getProductById(Integer productId) {
        return productRepository.findById(productId)
                .map(productResponseMapper::toDTO)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productResponseMapper::toDTO)
                .collect(Collectors.toList());
    }
}
