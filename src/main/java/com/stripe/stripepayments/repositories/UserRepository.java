package com.stripe.stripepayments.repositories;

import com.stripe.stripepayments.commons.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
