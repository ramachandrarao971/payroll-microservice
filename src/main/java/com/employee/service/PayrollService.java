package com.employee.service;

import com.employee.model.Payroll;
import com.employee.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.exception.ResourceNotFoundException;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;

@Service
public class PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    public Payroll updatePayrollSalary(String employeeId, Double ctc) {

        Payroll payroll = payrollRepository.findByEmployeeId(employeeId);

        if (payroll == null) {
            throw new ResourceNotFoundException("Payroll info not found for employee with id " + employeeId);
        }
        Double monthlySalary = calculateMonthlySalary(ctc);
        payroll.setMonthlySalary(monthlySalary);
        return payrollRepository.save(payroll);
    }

    public Payroll createPayroll(Payroll payroll) {
        return payrollRepository.save(payroll);
    }

    public Payroll getPayrollByEmployeeId(String employeeId) {
        Payroll payroll = payrollRepository.findByEmployeeId(employeeId);
        if (payroll == null) {
            throw new ResourceNotFoundException("Payroll info not found for employee with id " + employeeId);
        }
        return payroll;
    }

    private Double calculateMonthlySalary(Double ctc) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        Double format = Double.parseDouble(decimalFormat.format(ctc / 12));
        return format;
    }
}