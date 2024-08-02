package com.codecraftwithkevin.restapi.controller;

import com.codecraftwithkevin.restapi.entity.Customer;
import com.codecraftwithkevin.restapi.request.CustomerRequest;
import com.codecraftwithkevin.restapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping
    public Flux<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping
    public Mono<Customer> createCustomer(@RequestBody CustomerRequest request){
        return customerService.createCustomer(request);
    }

    @GetMapping("{id}")
    public Mono<Customer> getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @PutMapping("{id}")
    public Mono<Customer> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest request){
        return customerService.updateCustomer(id, request);
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteCustomer(@PathVariable Long id){
        return customerService.deleteCustomer(id);
    }
}
