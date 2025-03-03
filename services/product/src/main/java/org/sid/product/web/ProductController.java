package org.sid.product.web;

import jakarta.validation.Valid;
import org.sid.product.dtos.ProductRequestDto;
import org.sid.product.dtos.ProductResponseDto;
import org.sid.product.dtos.purchase.ProductPurchaseRequestDto;
import org.sid.product.dtos.purchase.ProductPurchaseResponseDto;
import org.sid.product.entities.Product;
import org.sid.product.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody @Valid ProductRequestDto product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponseDto>> purchaseProducts(@RequestBody @Valid List<ProductPurchaseRequestDto> productRequestDtos) {
        return new ResponseEntity<>(productService.purchaseProducts(productRequestDtos), HttpStatus.OK);
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable("product-id") Integer productId) {
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }


}
