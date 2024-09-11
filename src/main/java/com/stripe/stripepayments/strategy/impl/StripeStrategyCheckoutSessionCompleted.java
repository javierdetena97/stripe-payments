package com.stripe.stripepayments.strategy.impl;

import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.stripepayments.commons.entities.Payment;
import com.stripe.stripepayments.commons.enums.StripeEventEnum;
import com.stripe.stripepayments.repositories.PaymentRepository;
import com.stripe.stripepayments.strategy.StripeStrategy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StripeStrategyCheckoutSessionCompleted implements StripeStrategy {
    private final PaymentRepository paymentRepository;

    public StripeStrategyCheckoutSessionCompleted(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public boolean isApplicable(Event event) {
        return StripeEventEnum.CHECKOUT_SESSION_COMPLETED.value.equals(event.getType());
    }

    @Override
    public Event process(Event event) {
        var session = this.deserialize(event);
        return Optional.of(event)
                .map(givenEvent -> paymentRepository.findByPaymentId(session.getPaymentIntent()))
                .map(payment -> setProductId(payment, session.getMetadata().get("product_id")))
                .map(paymentRepository::save)
                .map(given -> event)
                .orElseThrow(() -> new RuntimeException("Error processing"));
    }

    private Payment setProductId(Payment payment, String productId) {
        payment.setProductId(productId);
        payment.setType(StripeEventEnum.CHECKOUT_SESSION_COMPLETED);
        return payment;
    }

    private Session deserialize(Event event) {
        return (Session) event.getDataObjectDeserializer().getObject()
                .orElseThrow(() -> new RuntimeException("Error deserializing"));
    }

}
