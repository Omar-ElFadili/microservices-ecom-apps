package org.sid.ecommerce.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationProducer {

    private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;

    public void sendPaymentNotification(PaymentNotificationRequest paymentNotificationRequest) {
        log.info("Sending payment notification request: {}", paymentNotificationRequest);
        Message<PaymentNotificationRequest> message = MessageBuilder
                .withPayload(paymentNotificationRequest)
                .setHeader(TOPIC, "payment-topic")
                .build();
        kafkaTemplate.send(message);
    }

}
