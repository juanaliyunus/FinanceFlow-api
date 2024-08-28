package com.enigma.loan.dto.response;

import com.enigma.loan.entity.Customer;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CustomerResponse {
    private String id;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String phone;

    private String status;

    public Customer toCustomerEntity() {
        return Customer.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(dateOfBirth)
                .phone(phone)
                .status(status)
                .build();
    }
}
