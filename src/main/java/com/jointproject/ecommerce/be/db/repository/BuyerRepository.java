package com.jointproject.ecommerce.be.db.repository;

import com.jointproject.ecommerce.be.db.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    public Buyer findByEmail(String email);
    public Buyer findByUserName(String userName);
    public Buyer findByEmailOrUserName(String email, String userName);
}
