package com.jointproject.ecommerce.be.db.repository;

import com.jointproject.ecommerce.be.db.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<SellerEntity, Long> {
    public SellerEntity findByEmail(String email);
    public SellerEntity findByUserName(String userName);
    public SellerEntity findByEmailOrUserName(String email, String userName);
}
