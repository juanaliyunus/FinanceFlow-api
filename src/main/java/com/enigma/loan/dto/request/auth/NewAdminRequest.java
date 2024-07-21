package com.enigma.loan.dto.request.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Setter Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewAdminRequest {
    private String username;
    private String password;
    private String name;
    private String email;
}
