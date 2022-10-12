package com.nttdata.bootcamp.project.Customer.service;

import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDto;
import com.nttdata.bootcamp.project.Customer.repository.ICustomerTypeRepository;
import com.nttdata.bootcamp.project.Customer.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerTypeService {

    @Autowired
    private ICustomerTypeRepository repository;

    public Flux<CustomerTypeDto> getCustomerTypes()
    {
        return repository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<CustomerTypeDto> getCustomerType(String id)
    {
        return repository.findById(id).map(AppUtils::entityToDto);
    }

    public Mono<CustomerTypeDto> saveCustomerType(Mono<CustomerTypeDto> customerTypeDtoMono)
    {
        return customerTypeDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<CustomerTypeDto> updateCustomerType(Mono<CustomerTypeDto> customerTypeDtoMono, String id)
    {
        return repository.findById(id)
                .flatMap(
                        p -> customerTypeDtoMono.map(AppUtils::dtoToEntity)
                                .doOnNext(e -> e.setId(id))
                )
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteCustomerType(String id)
    {
        return repository.deleteById(id);
    }
}
