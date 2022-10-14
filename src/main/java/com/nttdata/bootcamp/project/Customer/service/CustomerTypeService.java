package com.nttdata.bootcamp.project.Customer.service;

import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDto;
import com.nttdata.bootcamp.project.Customer.infrastructure.ICustomerTypeRepository;
import com.nttdata.bootcamp.project.Customer.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Qualifier("CustomerTypeService")
public class CustomerTypeService implements IGeneralService<CustomerTypeDto> {

    @Autowired
    private ICustomerTypeRepository repository;

    public Flux<CustomerTypeDto> getAll()
    {
        return repository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<CustomerTypeDto> getById(String id)
    {
        return repository.findById(id).map(AppUtils::entityToDto);
    }

    public Mono<CustomerTypeDto> save(Mono<CustomerTypeDto> customerTypeDtoMono)
    {
        return customerTypeDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<CustomerTypeDto> update(Mono<CustomerTypeDto> customerTypeDtoMono, String id)
    {
        return repository.findById(id)
                .flatMap(
                        p -> customerTypeDtoMono.map(AppUtils::dtoToEntity)
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
