package com.codecraftwithkevin.restapi.service;


import com.codecraftwithkevin.restapi.controller.exceptions.CustomerNotFoundException;
import com.codecraftwithkevin.restapi.entity.Customer;
import com.codecraftwithkevin.restapi.repository.CustomerRepository;
import com.codecraftwithkevin.restapi.request.CustomerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private  AtomicLong atomicLong = new AtomicLong();

    public Flux<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Mono<Customer> createCustomer(CustomerRequest request) {
        var id = atomicLong.incrementAndGet();
        var customer = new Customer(id,request.firstName(), request.lastName(),request.email());
        return customerRepository.save(customer);
    }

    public Mono<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Mono<Customer> updateCustomer(Long id, CustomerRequest request) {

     return  customerRepository.findById(id)
                .flatMap(existingCustomer->{
                    Customer updatedCustomer = new Customer(id, request.firstName(), request.lastName(), request.email());
                    return customerRepository.save(updatedCustomer);
                }).switchIfEmpty(Mono.error(new CustomerNotFoundException(id)));
    }

    public Mono<Void> deleteCustomer(Long id) {
        return customerRepository.deleteById(id);
    }
}
