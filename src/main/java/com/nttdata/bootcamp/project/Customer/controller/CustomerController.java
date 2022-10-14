package com.nttdata.bootcamp.project.Customer.controller;

import com.nttdata.bootcamp.project.Customer.dto.CustomerDto;
import com.nttdata.bootcamp.project.Customer.service.CustomerService;
import com.nttdata.bootcamp.project.Customer.service.ICustomerService;
import com.nttdata.bootcamp.project.Customer.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private ICustomerService service;

    @GetMapping
    public Flux<CustomerDto> getCustomers()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mono<CustomerDto> getCustomer(@PathVariable String id)
    {
        return service.getById(id);
    }

    @PostMapping
    public Mono<CustomerDto> saveCustomer(@RequestBody Mono<CustomerDto> customerDtoMono)
    {
        return service.save(customerDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<CustomerDto> updateCustomer(@RequestBody Mono<CustomerDto> customerDtoMono, @PathVariable String id)
    {
        return service.update(customerDtoMono,id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteCustomer(@PathVariable String id)
    {
        return service.delete(id);
    }
}
