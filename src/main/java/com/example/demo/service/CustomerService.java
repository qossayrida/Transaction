package com.example.demo.service;


import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private PaymentService paymentService;

    @Transactional
    public void addNewCustomerBy_REQUIRED(String customerName,boolean shouldFail) {
        customerRepository.save(Customer.builder().customerName(customerName).build());

        try {
            paymentService.performOperationBy_REQUIRED(shouldFail);
        } catch (RuntimeException e) {
            System.err.println("Caught exception but continuing current transaction");
        }
    }

    @Transactional
    public void addNewCustomerBy_REQUIRES_NEW(String customerName,boolean shouldFail) {
        customerRepository.save(Customer.builder().customerName(customerName).build());

        try {
            paymentService.performOperationBy_REQUIRES_NEW(shouldFail);
        } catch (RuntimeException e) {
            System.err.println("Caught exception but continuing current transaction");
        }
    }

    @Transactional
    public void addTwoPaymentTransactionWithTransactional() {
        paymentService.performOperationBy_Supports();
        paymentService.performOperationBy_NotSupported();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addTwoPaymentTransactionWithoutTransactional() {
        paymentService.performOperationBy_Supports();
        paymentService.performOperationBy_NotSupported();
    }

}

/*
        @Transactional(propagation = Propagation.REQUIRED):
            If a transaction already exists, the method will run within that transaction.
            If no transaction exists, a new transaction will be created for this method and any subsequent methods called within it.

        @Transactional(propagation = Propagation.REQUIRES_NEW)
           A new transaction will always be created for this method, regardless of whether a transaction already exists.
           The existing transaction (if any) will be suspended and resumed after this method completes.

        @Transactional(propagation = Propagation.SUPPORTS)
           If a transaction exists, it joins that transaction.
           If no transaction exists, it runs without creating a new one.

        @Transactional(propagation = Propagation.NOT_SUPPORTED)
           It will never run within a transaction, even if one exists.
 */