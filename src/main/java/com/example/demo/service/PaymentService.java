package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Payment;
import com.example.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void performOperationBy_REQUIRED(boolean shouldFail) {
        paymentRepository.save(Payment.builder().build());

        if (shouldFail) {
            throw new RuntimeException("Simulated failure!");
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void performOperationBy_REQUIRES_NEW(boolean shouldFail) {
        paymentRepository.save(Payment.builder().build());

        if (shouldFail) {
            throw new RuntimeException("Simulated failure!");
        }
    }



    @Transactional(propagation = Propagation.SUPPORTS)
    public void performOperationBy_Supports() {
        Payment payment = Payment.builder().build();

        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            payment.setStatus("Running within the existing transaction (SUPPORTS)");
        } else {
            payment.setStatus("No transaction present (SUPPORTS)");
        }
        paymentRepository.save(payment);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void performOperationBy_NotSupported() {
        Payment payment = Payment.builder().build();

        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            payment.setStatus("Running within the existing transaction (SUPPORTS)");
        } else {
            payment.setStatus("No transaction present (SUPPORTS)");
        }

        paymentRepository.save(payment);
    }
}
