package com.exercise.transaction.repository;

import com.exercise.transaction.model.SalaryPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryPaymentRepository extends JpaRepository<SalaryPayment, Long> {}
