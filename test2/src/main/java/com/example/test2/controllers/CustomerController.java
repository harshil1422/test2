package com.example.test2.controllers;
import com.example.test2.payloads.CustomerDto;
import com.example.test2.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
   //http://localhost:8080/customers/create
    @PostMapping("/create")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody @Validated CustomerDto customerDto) {
        CustomerDto createdCustomer = customerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }


    //http://localhost:8080/customers/getAll
    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerDto>> getAllCustomer( @RequestParam(value="pageNo",defaultValue = "0",required = false)int pageNo,
                                                             @RequestParam(value = "pageSize",defaultValue = "5",required = false)int pageSize,
                                                             @RequestParam(value = "sortBy",defaultValue = "name",required = false)String  sortBy,
                                                             @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir) {
        List<CustomerDto> allCustomer = customerService.getAllCustomer(pageNo,pageSize,sortDir,sortBy);
        return ResponseEntity.ok(allCustomer);
    }
    //http://localhost:8080/customers/get/id
    @GetMapping("/get/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long customerId) {
        CustomerDto customerDto = customerService.getCustomer(customerId);

            return ResponseEntity.ok(customerDto);
    }
    //http://localhost:8080/customers/put/id
    @PutMapping("/put/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long customerId,
                                                   @RequestBody @Validated CustomerDto customerDto) {
        CustomerDto updatedCustomer = customerService.updateCustomer(customerId, customerDto);
            return ResponseEntity.ok(updatedCustomer);
    }
    //http://localhost:8080/customers/delete/id
    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        boolean deleted = customerService.deleteCustomer(customerId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
