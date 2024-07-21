package com.enigma.loan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String id;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String phone;

    private String status;
}
