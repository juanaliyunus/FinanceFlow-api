package com.enigma.loan.dto.request.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Setter Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewStaffRequest {
    private String username;
    private String password;
    private String name;
    private String email;
}
