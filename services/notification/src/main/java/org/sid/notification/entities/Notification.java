package org.sid.notification.entities;

import lombok.*;
import org.sid.notification.enums.NotificationType;
import org.sid.notification.kafka.models.OrderConfirmation;
import org.sid.notification.kafka.models.PaymentConfirmation;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Notification {

    private String id;
    private NotificationType type;
    private LocalDateTime notificationTime;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;

}
