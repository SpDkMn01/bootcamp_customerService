package com.nttdata.bootcamp.project.Customer.controller;

import com.nttdata.bootcamp.project.Customer.dto.CustomerDto;
import com.nttdata.bootcamp.project.Customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public Flux<CustomerDto> getCustomers()
    {
        return service.getCustomers();
    }

    @GetMapping("/{id}")
    public Mono<CustomerDto> getCustomer(@PathVariable String id)
    {
        return service.getCustomer(id);
    }

    @PostMapping
    public Mono<CustomerDto> saveCustomer(@RequestBody Mono<CustomerDto> customerDtoMono)
    {
        return service.saveCustomer(customerDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<CustomerDto> updateCustomer(@RequestBody Mono<CustomerDto> customerDtoMono, @PathVariable String id)
    {
        return service.updateCustomer(customerDtoMono,id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteCustomer(@PathVariable String id)
    {
        return service.deleteCustomer(id);
    }
}
