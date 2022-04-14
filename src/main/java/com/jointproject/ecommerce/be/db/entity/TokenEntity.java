package com.jointproject.ecommerce.be.db.entity;

import com.jointproject.ecommerce.be.utility.enums.TokenStatus;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Slf4j
@Getter
@Setter
@ToString
@Table
public class TokenEntity extends BaseEntity {
    @Column(name = "wallet_Id")
    @NotBlank
    @NotNull
    private String walletId;

    @Column(name = "email")
    @NotBlank
    @NotNull
    private String email;

    @Column(name = "token_id")
    @NotBlank
    @NotNull
    private String tokenId;

    @Column(name = "token")
    @NotBlank
    @NotNull
    private String token;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Instant expiredAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TokenStatus status = TokenStatus.ACTIVE;
}