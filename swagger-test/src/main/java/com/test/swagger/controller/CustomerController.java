package com.test.swagger.controller;

import com.test.swagger.model.Customer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private Map<Long, Customer> customerDB = new HashMap<>();
    private Long idCounter = 1L;

    @Operation(summary = "Create a new customer", description = "Adds a new customer")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Customer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        customer.setId(idCounter++);
        customerDB.put(customer.getId(), customer);
        return customer;
    }

    @Operation(summary = "Get customer by ID", description = "Find a customer by their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer found"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @GetMapping("/{id}")
    public Customer getCustomerById(
            @Parameter(description = "ID of the customer to fetch") @PathVariable Long id) {
        return customerDB.get(id);
    }

    @Operation(summary = "Get all customers", description = "Returns a list of all customers")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of customers retrieved successfully")
    })
    @GetMapping
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerDB.values());
    }

    @Operation(summary = "Update customer", description = "Updates customer details by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        if (customerDB.containsKey(id)) {
            customer.setId(id);
            customerDB.put(id, customer);
            return customer;
        }
        return null;
    }

    @Operation(summary = "Delete customer", description = "Deletes customer by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        if (customerDB.containsKey(id)) {
            customerDB.remove(id);
            return "Customer with ID " + id + " deleted.";
        }
        return "Customer not found.";
    }
}
