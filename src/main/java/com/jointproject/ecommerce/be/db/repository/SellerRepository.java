package com.jointproject.ecommerce.be.db.repository;

import com.jointproject.ecommerce.be.db.entity.Buyer;
import com.jointproject.ecommerce.be.db.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    public Seller findByEmail(String email);
    public Seller findByUserName(String userName);
    public Seller findByEmailOrUserName(String email, String userName);
}
