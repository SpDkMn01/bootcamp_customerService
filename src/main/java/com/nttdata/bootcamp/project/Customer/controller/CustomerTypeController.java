package com.nttdata.bootcamp.project.Customer.controller;

import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDto;
import com.nttdata.bootcamp.project.Customer.service.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/customerTypes")
public class CustomerTypeController {

    @Autowired
    private CustomerTypeService service;

    @GetMapping
    public Flux<CustomerTypeDto> getCustomerTypes()
    {
        return service.getCustomerTypes();
    }

    @GetMapping("/{id}")
    public Mono<CustomerTypeDto> getCustomerType(@PathVariable String id)
    {
        return service.getCustomerType(id);
    }

    @PostMapping
    public Mono<CustomerTypeDto> saveCustomerType(@RequestBody Mono<CustomerTypeDto> customerTypeDtoMono)
    {
        return service.saveCustomerType(customerTypeDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<CustomerTypeDto> updateCustomerType(@RequestBody Mono<CustomerTypeDto> customerTypeDtoMono, @PathVariable String id)
    {
        return service.updateCustomerType(customerTypeDtoMono,id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteCustomer(@PathVariable String id)
    {
        return service.deleteCustomerType(id);
    }
}
