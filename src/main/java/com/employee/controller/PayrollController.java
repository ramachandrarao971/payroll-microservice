package com.employee.controller;

import com.employee.model.Payroll;
import com.employee.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import org.json.JSONObject;

import java.util.Map;

@RestController
@RequestMapping("/payroll")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @GetMapping("/{employeeId}")
    public Payroll getPayrollByEmployeeId(@PathVariable("employeeId") String employeeId) {
        return payrollService.getPayrollByEmployeeId(employeeId);
    }

    @PostMapping("/")
    public Payroll createPayroll(@RequestBody Payroll payroll) {
        return payrollService.createPayroll(payroll);
    }

    @PostMapping("/{employeeId}")
    public Payroll updatePayrollSalary(@PathVariable("employeeId") String employeeId, @RequestBody Map<String, Object> requestBody) {
        double ctc = Double.parseDouble(requestBody.get("ctc").toString());

        return payrollService.updatePayrollSalary(employeeId, ctc);
    }
}
