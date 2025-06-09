package com.handler.exception.service;

import com.handler.exception.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO getCustomerById(String id);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(String id, CustomerDTO customerDTO);

    void deleteCustomer(String id);
}
