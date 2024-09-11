package com.stripe.stripepayments.commons.entities;

import com.stripe.stripepayments.commons.enums.StripeEventEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @SequenceGenerator(
            name = "payment_sequence",
            sequenceName = "payment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_sequence"
    )
    private Long id;
    private String paymentIntentId;
    private String customerId;
    private String productId;
    private Long amount;
    private String currency;
    @Enumerated(EnumType.STRING)
    private StripeEventEnum type;
}
