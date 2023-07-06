package com.example.test2.services;

import com.example.test2.entities.Customer;
import com.example.test2.payloads.CustomerDto;

import java.util.List;

public interface CustomerService  {
    CustomerDto createCustomer(CustomerDto customerPayload);


    List<CustomerDto> getAllCustomer(int pageNo,int pageSize,String sortDir,String sortBy);

    CustomerDto getCustomer(Long customerId);

    CustomerDto updateCustomer(Long customerId, CustomerDto customerDto);

    boolean deleteCustomer(Long customerId);
}
