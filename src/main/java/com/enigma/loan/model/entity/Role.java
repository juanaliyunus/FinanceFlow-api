package com.enigma.loan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private ERole role;

    public enum ERole {
        ROLE_CUSTOMER,
        ROLE_STAFF,
        ROLE_ADMIN
    }

}
