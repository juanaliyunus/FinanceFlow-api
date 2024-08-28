package com.enigma.loan.dto.response;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTClaims {
    private String userId;
    private Set<String> roles;
}
