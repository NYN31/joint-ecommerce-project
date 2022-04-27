package com.jointproject.ecommerce.be.db.repository;

import com.jointproject.ecommerce.be.db.entity.BuyerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerEntity, Long> {
    public BuyerEntity findByEmail(String email);
    public BuyerEntity findByUserName(String userName);
    public BuyerEntity findByEmailOrUserName(String email, String userName);
}
