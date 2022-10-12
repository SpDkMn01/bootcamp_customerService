package com.nttdata.bootcamp.project.Customer.service;

import com.nttdata.bootcamp.project.Customer.dto.CustomerDto;
import com.nttdata.bootcamp.project.Customer.repository.CustomerRepository;
import com.nttdata.bootcamp.project.Customer.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Flux<CustomerDto> getCustomers()
    {
        return repository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<CustomerDto> getCustomer(String id)
    {
        return repository.findById(id).map(AppUtils::entityToDto);
    }

    public Mono<CustomerDto> saveCustomer(Mono<CustomerDto> customerDtoMono)
    {
        return customerDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<CustomerDto> updateCustomer(Mono<CustomerDto> customerDtoMono, String id)
    {
        return repository.findById(id)
                .flatMap(
                        p -> customerDtoMono.map(AppUtils::dtoToEntity)
                            .doOnNext(e -> e.setId(id))
                )
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteCustomer(String id)
    {
        return repository.deleteById(id);
    }
}
