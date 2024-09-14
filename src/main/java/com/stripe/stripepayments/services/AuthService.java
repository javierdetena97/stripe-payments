package com.stripe.stripepayments.services;

import com.stripe.stripepayments.commons.dtos.AuthResponseDto;
import com.stripe.stripepayments.commons.dtos.UserRequest;

public interface AuthService {
    AuthResponseDto createUser(UserRequest userRequest);
}
