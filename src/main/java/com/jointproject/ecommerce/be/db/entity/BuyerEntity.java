package com.jointproject.ecommerce.be.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class BuyerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Length(min = 5, max = 255)
    @Column(name = "user_name", unique = true, length = 255)
    private String userName;

    @Column(name = "email", unique = true, length = 255)
    private String email;

    @Length(min = 6)
    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "image")
    private String image;

    @Column(name = "address")
    private String address;

    @Column(name = "balance")
    private Double balance = 0.0;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", updatable = true, nullable = false)
    private Instant updatedAt;
}
