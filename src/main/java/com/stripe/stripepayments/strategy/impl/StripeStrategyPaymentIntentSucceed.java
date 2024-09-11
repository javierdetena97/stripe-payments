package com.stripe.stripepayments.strategy.impl;

import com.stripe.model.Event;
import com.stripe.stripepayments.commons.enums.StripeEventEnum;
import com.stripe.stripepayments.strategy.StripeStrategy;
import org.springframework.stereotype.Component;

@Component
public class StripeStrategyPaymentIntentSucceed implements StripeStrategy {

    @Override
    public boolean isApplicable(Event event) {
        return StripeEventEnum.PAYMENT_INTENT_SUCCEED.equals(event.getType());
    }

    @Override
    public Event process(Event event) {
        return null;
    }
}
