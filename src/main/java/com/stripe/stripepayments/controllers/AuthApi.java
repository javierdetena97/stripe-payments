package com.stripe.stripepayments.controllers;

import com.stripe.stripepayments.commons.constants.ApiPath;
import com.stripe.stripepayments.commons.dtos.AuthResponseDto;
import com.stripe.stripepayments.commons.dtos.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPath.V1_ROUTE + ApiPath.AUTH_ROUTE)
public interface AuthApi {
    @PostMapping
    ResponseEntity<AuthResponseDto> createUser(@RequestBody @Valid UserRequest userRequest);
}
