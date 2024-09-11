package com.stripe.stripepayments.repositories;

import com.stripe.stripepayments.commons.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByPaymentId(String paymentId);
}
