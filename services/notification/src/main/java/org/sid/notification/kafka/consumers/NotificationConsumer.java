package org.sid.notification.kafka.consumers;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.sid.notification.entities.Notification;
import org.sid.notification.enums.NotificationType;
import org.sid.notification.kafka.models.OrderConfirmation;
import org.sid.notification.kafka.models.PaymentConfirmation;
import org.sid.notification.repositories.NotificationRepository;
import org.sid.notification.services.email.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.sid.notification.enums.NotificationType.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        notificationRepository.save(
                Notification.builder()
                        .notificationTime(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .type(PAYMENT_CONFIRMATION)
                        .build())
        ;

        // sending email
        String customerName = paymentConfirmation.getCustomerFirstName() + " " + paymentConfirmation.getCustomerLastName();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.getCustomerEmail(),
                customerName,
                paymentConfirmation.getAmount(),
                paymentConfirmation.getOrderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        notificationRepository.save(
                Notification.builder()
                        .notificationTime(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .type(ORDER_CONFIRMATION)
                        .build())
        ;
        // sending email
        String customerName = orderConfirmation.getCustomer().getFirstName() + " " + orderConfirmation.getCustomer().getLastName();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.getCustomer().getEmail(),
                customerName,
                orderConfirmation.getTotalAmount(),
                orderConfirmation.getOrderReference(),
                orderConfirmation.getProducts()
        );
    }
}
