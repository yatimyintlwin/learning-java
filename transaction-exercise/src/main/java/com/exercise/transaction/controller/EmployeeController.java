package com.exercise.transaction.controller;

import com.exercise.transaction.dto.HireRequest;
import com.exercise.transaction.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/hire")
    public String hireEmployee(@RequestBody HireRequest request) {
        try {
            employeeService.hireEmployeeWithSalary(request.getName(), request.getSalary());
            System.out.println("sending email with hire employee with salary: ");
            return "Employee " + request.getName() + " hired with salary " + request.getSalary();
        } catch (Exception e) {
            return "Transaction rolled back: " + e.getMessage();
        }
    }
}
