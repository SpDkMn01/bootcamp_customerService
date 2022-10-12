package com.nttdata.bootcamp.project.Customer.utils;

import com.nttdata.bootcamp.project.Customer.dto.CustomerDto;
import com.nttdata.bootcamp.project.Customer.entity.Customer;
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
}
