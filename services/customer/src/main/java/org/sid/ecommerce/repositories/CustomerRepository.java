package org.sid.ecommerce.repositories;

import org.sid.ecommerce.documents.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
