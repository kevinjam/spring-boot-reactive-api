package com.codecraftwithkevin.restapi.repository;

import com.codecraftwithkevin.restapi.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
}
