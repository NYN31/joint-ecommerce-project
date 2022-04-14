package com.jointproject.ecommerce.be.db.repository;

import com.jointproject.ecommerce.be.db.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
//    TokenEntity findByToken(String token);
//    TokenEntity findByTokenId(String tokenId);
}
