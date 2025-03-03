package org.sid.ecommerce.documents;

import lombok.*;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Document
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
}
