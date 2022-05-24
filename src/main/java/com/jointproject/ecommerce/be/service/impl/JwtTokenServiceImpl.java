package com.jointproject.ecommerce.be.service.impl;

import com.jointproject.ecommerce.be.db.entity.TokenEntity;
import com.jointproject.ecommerce.be.db.repository.TokenRepository;
import com.jointproject.ecommerce.be.exception.ApiException;
import com.jointproject.ecommerce.be.pojo.response.CommonResponse;
import com.jointproject.ecommerce.be.pojo.response.JwtTokenResponse;
import com.jointproject.ecommerce.be.pojo.response.ResultResponse;
import com.jointproject.ecommerce.be.service.JwtTokenService;
import com.jointproject.ecommerce.be.utility.enums.ResultStatus;
import com.jointproject.ecommerce.be.utility.enums.TokenStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final TokenRepository tokenRepository;

    public JwtTokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Value("${token.signature.secret.key.base64}")
    public String SECRETE_KEY;
    @Value("${token.time.to.live.in.millis}")
    public Long ttlMillis;
    @Value("${token.issuer}")
    public String issuer;
    @Value("${token.subject}")
    public String subject;

    @Override
    public String jwtTokenCreation(String email) {
        return create(email);
    }

    private String create(String email) {
        log.info("Enter into getToken function.");
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        String id = UUID.randomUUID().toString();

        Long nowMilliSecond = System.currentTimeMillis();
        Date issuedTime = new Date(nowMilliSecond); // issued time here
        Long expMilliSecond = nowMilliSecond + ttlMillis;
        Date expirationTime = new Date(expMilliSecond); // expired time here

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRETE_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // Token building
        String token = Jwts.builder()
                .setId(id)
                .setIssuedAt(issuedTime)
                .setExpiration(expirationTime)
                .setSubject(subject)
                .setIssuer(issuer)
                .claim("emailAddress", email)
                .signWith(signatureAlgorithm, signingKey).compact();

        TokenEntity tokenEntity = TokenEntity.builder()
                .tokenId(id)
                .token(token)
                .issuedAt(issuedTime)
                .expiredAt(expirationTime)
                .status(TokenStatus.ACTIVE)
                .email(email)
                .build();
        tokenRepository.save(tokenEntity);

        return token;
    }

    @Override
    public ResultResponse verify(String token) {
        log.info("Enter into verifyToken function.");
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRETE_KEY))
                    .parseClaimsJws(token).getBody();

            String tokenId = (String) claims.getId();
            String emailAddress = (String) claims.get("emailAddress");

            Optional<TokenEntity> tokenDetails =
                    Optional.ofNullable(tokenRepository.findByTokenId(tokenId));
            if(!tokenDetails.isPresent() || tokenDetails.get().getStatus() == TokenStatus.INACTIVE) {
                throw new ApiException();
            }

            CommonResponse response = new CommonResponse();
            response.setCode(ResultStatus.TOKEN_VALID.getValue());
            response.setResultStatus(ResultStatus.TOKEN_VALID);

            return createResultResponse(response);
        } catch (ExpiredJwtException exp) {
            throw new ApiException(ResultStatus.TOKEN_EXPIRED, ResultStatus.TOKEN_EXPIRED.getValue());
        } catch (Exception e) {
            throw new ApiException(ResultStatus.TOKEN_INVALID, ResultStatus.TOKEN_INVALID.getValue());
        }
    }

    private ResultResponse createResultResponse(Object object) {
        return ResultResponse.builder()
                .value(0)
                .data(object)
                .build();
    }
}
