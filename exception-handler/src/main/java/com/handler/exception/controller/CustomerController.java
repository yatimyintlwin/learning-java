package com.handler.exception.controller;

import com.handler.exception.dto.CustomerDTO;
import com.handler.exception.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerDTO create(@RequestBody CustomerDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    @GetMapping("/{id}")
    public CustomerDTO getById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public List<CustomerDTO> getAll() {
        return customerService.getAllCustomers();
    }

    @PutMapping("/{id}")
    public CustomerDTO update(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return "Customer with ID " + id + " deleted successfully.";
    }
}
