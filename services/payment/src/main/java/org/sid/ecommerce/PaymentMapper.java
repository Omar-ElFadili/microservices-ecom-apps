package org.sid.ecommerce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.sid.ecommerce.dtos.PaymentRequestDto;
import org.sid.ecommerce.dtos.PaymentResponseDto;
import org.sid.ecommerce.entities.Payment;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@Component
public class PaymentMapper {

    public Payment toPaymentEntity(PaymentRequestDto paymentRequestDto) {
        Payment payment = Payment.builder()
                .id(paymentRequestDto.getId())
                .paymentMethod(paymentRequestDto.getPaymentMethod())
                .orderId(paymentRequestDto.getOrderId())
                .amount(paymentRequestDto.getAmount())
                .build();

        return payment;
    }

    public PaymentResponseDto toPaymentDto(Payment payment) {
        return new PaymentResponseDto(
                payment.getId(),
                payment.getPaymentMethod(),
                payment.getOrderId(),
                payment.getAmount(),
                null,
                null
        );
    }
}
