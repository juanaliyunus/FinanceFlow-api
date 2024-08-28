package com.enigma.loan.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.enigma.loan.dto.response.JWTClaims;
import com.enigma.loan.entity.User;
import com.enigma.loan.service.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.HashSet;

@Service
@Slf4j
public class JWTServiceImpl implements JWTService {
    private final String JWT_SECRET = "Admin#1234";
    private final String ISSUER = "loan";
    private final long JWT_EXPIRATION = 36000;

    @Override
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
            return JWT.create()
                    .withSubject(user.getId())
                    .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusSeconds(JWT_EXPIRATION))
                    .withIssuer(ISSUER)
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,  "error while creating jwt token");
        }
    }

    @Override
    public boolean verifyJwtToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
            JWTVerifier jwtVerifier  = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            jwtVerifier.verify(parseJwt(token));
            return true;
        }catch (JWTVerificationException e){
            return false;
        }
    }

    @Override
    public JWTClaims getClaimsByToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT decodedJWT = jwtVerifier.verify(parseJwt(token));
            return JWTClaims.builder()
                    .userId(decodedJWT.getSubject())
                    .roles(new HashSet<>(decodedJWT.getClaim("roles").asList(String.class)))
                    .build();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    private String parseJwt(String token){
        if(token != null && token.startsWith("Bearer ")){
            return token.substring(7);
        }
        return null;
    }
}
