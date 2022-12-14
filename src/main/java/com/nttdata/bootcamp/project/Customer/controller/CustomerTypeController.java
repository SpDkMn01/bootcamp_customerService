package com.nttdata.bootcamp.project.Customer.controller;

import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDto;
import com.nttdata.bootcamp.project.Customer.service.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/customerTypes")
public class CustomerTypeController {

    @Autowired
    @Qualifier("CustomerTypeService")
    private CustomerTypeService service;

    @GetMapping
    public Flux<CustomerTypeDto> getCustomerTypes()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mono<CustomerTypeDto> getCustomerType(@PathVariable String id)
    {
        return service.getById(id);
    }

    @PostMapping
    public Mono<CustomerTypeDto> saveCustomerType(@RequestBody Mono<CustomerTypeDto> customerTypeDtoMono)
    {
        return service.save(customerTypeDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<CustomerTypeDto> updateCustomerType(@RequestBody Mono<CustomerTypeDto> customerTypeDtoMono, @PathVariable String id)
    {
        return service.update(customerTypeDtoMono,id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteCustomer(@PathVariable String id)
    {
        return service.delete(id);
    }
}
