package com.example.demo.controller;

import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @GetMapping("/create-financial-transaction-Required")
    public void addBy_REQUIRED(@RequestParam String customerName,@RequestParam boolean shouldFail) {
        customerService.addNewCustomerBy_REQUIRED(customerName,shouldFail);
    }

    @GetMapping("/create-financial-transaction-request-new")
    public void addBy_REQUIRES_NEW(@RequestParam String customerName,@RequestParam boolean shouldFail) {
        customerService.addNewCustomerBy_REQUIRES_NEW(customerName,shouldFail);
    }

    @GetMapping("/create-two-financial-transaction")
    public void addTwoPaymentTransactionWithTransactional() {
        customerService.addTwoPaymentTransactionWithTransactional();
    }

    @GetMapping("/create-two-financial-transaction-WithoutTransactional")
    public void addTwoPaymentTransactionWithoutTransactional() {
        customerService.addTwoPaymentTransactionWithoutTransactional();
    }

}
