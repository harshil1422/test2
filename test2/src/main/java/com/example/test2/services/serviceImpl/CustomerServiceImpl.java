package com.example.test2.services.serviceImpl;

import com.example.test2.entities.Customer;
import com.example.test2.entities.Details;
import com.example.test2.exception.ReSourceNotFoundException;
import com.example.test2.payloads.CustomerDto;
import com.example.test2.repository.CustomerRepository;
import com.example.test2.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private  final CustomerRepository  repo;

    private final ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }


    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = mapToEntity(customerDto);
        Customer save = repo.save(customer);
       return mapToDto(save);
    }

    @Override
    public List<CustomerDto> getAllCustomer(int pageNo, int pageSize, String sortDir,  String sortBy) {
        Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= (Pageable) PageRequest.of(pageNo,pageSize,sort);
        Page<Customer> page = repo.findAll((org.springframework.data.domain.Pageable) pageable);
        List<Customer> customerList = page.toList();
        List<CustomerDto> customerDtoList = customerList.stream().map(customer -> mapToDto(customer)).collect(Collectors.toList());
       return customerDtoList;
    }

    @Override
    public CustomerDto getCustomer(Long customerId) {
        Customer customer = repo.findById(customerId).orElseThrow(() -> new ReSourceNotFoundException("Customer", "CustomerId", customerId));
        return mapToDto(customer);
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) {
        Customer customer = repo.findById(customerId).orElseThrow(() -> new ReSourceNotFoundException("Customer", "CustomerId", customerId));
        customer.setAccountType(customerDto.getAccountType());
        customer.setName(customerDto.getName());
        customer.setDetails(modelMapper.map(customerDto.getDetails(),Details.class) );
        customer.setBusinessRequirements(customerDto.getBusinessRequirements());

        Customer save = repo.save(customer);
        return mapToDto(save);
    }

    @Override
    public boolean deleteCustomer(Long customerId) {
        Customer customer = repo.findById(customerId).orElseThrow(() -> new ReSourceNotFoundException("Customer", "CustomerId", customerId));
        repo.deleteById(customerId);
        return true;
    }

    private Customer mapToEntity(CustomerDto customerDto){
      return   modelMapper.map(customerDto,Customer.class);
    }
    private CustomerDto mapToDto (Customer customer){
        return   modelMapper.map(customer,CustomerDto.class);
    }
}
