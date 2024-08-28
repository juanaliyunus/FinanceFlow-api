package com.enigma.loan.dto.response;

import com.enigma.loan.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String id;
    private String username;
    private String token;
    private String massage;
    private Set<String> roles;
}
