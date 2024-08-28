package com.enigma.loan.service;

import com.enigma.loan.dto.response.JWTClaims;
import com.enigma.loan.entity.User;

public interface JWTService {
    String generateToken(User user);
    boolean verifyJwtToken(String token);
    JWTClaims getClaimsByToken(String token);
}
