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
import java.util.Date;

@Slf4j
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "token_details")
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "issued_at", nullable = false)
    @CreationTimestamp
    private Date issuedAt;

    @Column(name = "expired_at", nullable = false)
    @UpdateTimestamp
    private Date expiredAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TokenStatus status = TokenStatus.ACTIVE;

    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at", updatable = true, nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;
}