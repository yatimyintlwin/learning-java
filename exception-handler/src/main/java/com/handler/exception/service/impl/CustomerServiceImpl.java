package com.handler.exception.service.impl;

import com.handler.exception.dto.CustomerDTO;
import com.handler.exception.exception.CustomerNotFoundException;
import com.handler.exception.exception.InvalidCustomerException;
import com.handler.exception.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final Map<String, CustomerDTO> customerMap = new HashMap<>();

    @Override
    public CustomerDTO getCustomerById(String id) {
        if (!customerMap.containsKey(id)) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        }
        return customerMap.get(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerMap.values().stream().toList();
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        if (customerDTO.getId() == null || customerDTO.getId().isBlank()) {
            throw new InvalidCustomerException("Customer ID must not be empty.");
        }
        if (customerDTO.getName() == null || customerDTO.getName().isBlank()) {
            throw new InvalidCustomerException("Customer name must not be empty.");
        }
        if (customerMap.containsKey(customerDTO.getId())) {
            throw new InvalidCustomerException("Customer with ID " + customerDTO.getId() + " already exists.");
        }
        customerMap.put(customerDTO.getId(), customerDTO);
        return customerDTO;
    }

    @Override
    public CustomerDTO updateCustomer(String id, CustomerDTO customerDTO) {
        if (!customerMap.containsKey(id)) {
            throw new CustomerNotFoundException("Cannot update. Customer with ID " + id + " not found.");
        }
        if (customerDTO.getId() == null) {
            throw new InvalidCustomerException("Customer ID must not be null.");
        }
        if (customerDTO.getName() == null || customerDTO.getName().isBlank()) {
            throw new InvalidCustomerException("Customer name must not be empty.");
        }
        customerDTO.setId(id);
        customerMap.put(id, customerDTO);
        return customerDTO;
    }

    @Override
    public void deleteCustomer(String id) {
        if (!customerMap.containsKey(id)) {
            throw new CustomerNotFoundException("Cannot delete. Customer with ID " + id + " not found.");
        }
        customerMap.remove(id);
    }
}