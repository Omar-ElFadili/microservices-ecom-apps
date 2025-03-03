package org.sid.ecommerce.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sid.ecommerce.PaymentMapper;
import org.sid.ecommerce.dtos.PaymentRequestDto;
import org.sid.ecommerce.entities.Payment;
import org.sid.ecommerce.kafka.NotificationProducer;
import org.sid.ecommerce.kafka.PaymentNotificationRequest;
import org.sid.ecommerce.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequestDto paymentRequestDto) {
        Payment payment = paymentMapper.toPaymentEntity(paymentRequestDto);

        PaymentNotificationRequest paymentNotificationRequest = PaymentNotificationRequest.builder()
                .paymentMethod(paymentRequestDto.getPaymentMethod())
                .amount(paymentRequestDto.getAmount())
                .customerEmail(paymentRequestDto.getCustomer().getEmail())
                .customerFirstName(paymentRequestDto.getCustomer().getFirstName())
                .customerLastName(paymentRequestDto.getCustomer().getLastName())
                .orderReference(paymentRequestDto.getOrderReference())
                .build();
        notificationProducer.sendPaymentNotification(paymentNotificationRequest);

        return paymentRepository.save(payment).getId();
    }
}
