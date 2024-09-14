package com.stripe.stripepayments.services.impl;

import com.stripe.stripepayments.commons.dtos.AuthResponseDto;
import com.stripe.stripepayments.commons.dtos.UserRequest;
import com.stripe.stripepayments.commons.entities.User;
import com.stripe.stripepayments.repositories.UserRepository;
import com.stripe.stripepayments.services.AuthService;
import com.stripe.stripepayments.services.StripeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final StripeService stripeService;
    private final UserRepository userRepository;

    public AuthServiceImpl(StripeService stripeService, UserRepository userRepository) {
        this.stripeService = stripeService;
        this.userRepository = userRepository;
    }

    @Override
    public AuthResponseDto createUser(UserRequest userRequest) {
        return Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(this::setUserCustomerAndProduct)
                .map(userRepository::save)
                .map(userModel -> AuthResponseDto.builder()
                        .customerId(userModel.getCustomerId())
                        .productId(userModel.getProductId())
                        .build()
                )
                .orElseThrow(() -> new RuntimeException("Error creating user"));
    }

    private User setUserCustomerAndProduct(User user) {
        var customerCreated = stripeService.createCustomer(user.getEmail());
        var productCreated = stripeService.createProduct(user.getName());
        stripeService.createPrice(productCreated.getId());

        user.setProductId(productCreated.getId());
        user.setCustomerId(customerCreated.getId());

        return user;
    }

    private User mapToEntity(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }
}
