package com.jointproject.ecommerce.be.db.repository;

import com.jointproject.ecommerce.be.db.entity.BuyerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerEntity, Long> {
    BuyerEntity findByEmail(String email);
    BuyerEntity findByUserName(String userName);BuyerEntity findByEmailOrUserName(String email, String userName);
}
