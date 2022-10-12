package com.nttdata.bootcamp.project.Customer.repository;

import com.nttdata.bootcamp.project.Customer.entity.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
}
