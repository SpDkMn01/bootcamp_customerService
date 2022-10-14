package com.nttdata.bootcamp.project.Customer.service;

import com.nttdata.bootcamp.project.Customer.dto.CustomerDto;
import com.nttdata.bootcamp.project.Customer.infrastructure.ICustomerRepository;
import com.nttdata.bootcamp.project.Customer.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class CustomerService implements ICustomerService<CustomerDto> {

    @Autowired
    private ICustomerRepository repository;

    public Flux<CustomerDto> getAll()
    {
        return repository.findAll().delayElements(Duration.ofSeconds(1)).map(AppUtils::entityToDto);
    }

    public Mono<CustomerDto> getById(String id)
    {
        return repository.findById(id).map(AppUtils::entityToDto);
    }

    public Mono<CustomerDto> save(Mono<CustomerDto> customerDtoMono)
    {
        return customerDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<CustomerDto> update(Mono<CustomerDto> customerDtoMono, String id)
    {
        return repository.findById(id)
                .flatMap(
                        p -> customerDtoMono.map(AppUtils::dtoToEntity)
                            .doOnNext(e -> e.setId(id))
                )
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<Void> delete(String id)
    {
        return repository.deleteById(id);
    }
}
