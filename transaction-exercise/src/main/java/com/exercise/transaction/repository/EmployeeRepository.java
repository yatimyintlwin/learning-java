package com.exercise.transaction.repository;

import com.exercise.transaction.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByName(String name);
}
