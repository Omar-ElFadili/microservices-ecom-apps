package org.sid.notification.kafka.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String email;

}
