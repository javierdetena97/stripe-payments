package com.stripe.stripepayments.controllers;

import com.stripe.stripepayments.commons.constants.ApiPath;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPath.V1_ROUTE + ApiPath.STRIPE_ROUTE)
public interface StripeApi {
    @PostMapping(value = "/webhook")
    ResponseEntity<Void> stripeWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String stripeHeader
    );
}
