package org.sid.notification.repositories;

import org.sid.notification.entities.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification , String> {
}
