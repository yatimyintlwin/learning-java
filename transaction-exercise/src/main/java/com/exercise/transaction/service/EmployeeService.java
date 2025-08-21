package com.exercise.transaction.service;

import com.exercise.transaction.model.Employee;
import com.exercise.transaction.model.SalaryPayment;
import com.exercise.transaction.repository.EmployeeRepository;
import com.exercise.transaction.repository.SalaryPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final SalaryPaymentRepository salaryPaymentRepository;

    @Transactional
    public void hireEmployeeWithSalary(String name, BigDecimal salary) {
        Employee emp = new Employee(null, name);
        emp = employeeRepository.save(emp);

        SalaryPayment payment = new SalaryPayment(null, salary, LocalDateTime.now(), emp);
        salaryPaymentRepository.save(payment);
    }
}
