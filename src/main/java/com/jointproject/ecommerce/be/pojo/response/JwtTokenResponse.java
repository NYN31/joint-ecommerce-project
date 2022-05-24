package com.jointproject.ecommerce.be.pojo.response;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenResponse {
    private String token;
}
