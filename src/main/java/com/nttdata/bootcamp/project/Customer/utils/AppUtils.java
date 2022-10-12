package com.nttdata.bootcamp.project.Customer.utils;

import com.nttdata.bootcamp.project.Customer.dto.CustomerDto;
import com.nttdata.bootcamp.project.Customer.dto.CustomerTypeDto;
import com.nttdata.bootcamp.project.Customer.entity.Customer;
import com.nttdata.bootcamp.project.Customer.entity.CustomerType;
import org.springframework.beans.BeanUtils;

public class AppUtils {
    public static CustomerDto entityToDto(Customer customer)
    {
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);
        return customerDto;
    }

    public static Customer dtoToEntity(CustomerDto customerDto)
    {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        return customer;
    }

    public static CustomerTypeDto entityToDto(CustomerType customerType)
    {
        CustomerTypeDto customerTypeDto = new CustomerTypeDto();
        BeanUtils.copyProperties(customerType, customerTypeDto);
        return customerTypeDto;
    }

    public static CustomerType dtoToEntity(CustomerTypeDto customerTypeDto)
    {
        CustomerType customerType = new CustomerType();
        BeanUtils.copyProperties(customerTypeDto, customerType);
        return customerType;
    }
}
