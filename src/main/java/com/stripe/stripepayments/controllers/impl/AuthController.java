package com.stripe.stripepayments.controllers.impl;

import com.stripe.stripepayments.commons.dtos.AuthResponseDto;
import com.stripe.stripepayments.commons.dtos.UserRequest;
import com.stripe.stripepayments.controllers.AuthApi;
import com.stripe.stripepayments.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<AuthResponseDto> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(authService.createUser(userRequest));
    }
}
