package com.employee.repository;

import com.employee.model.Payroll;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollRepository extends MongoRepository<Payroll, String> {

    Payroll findByEmployeeId(String employeeId);
}