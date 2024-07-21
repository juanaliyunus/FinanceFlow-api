package com.enigma.loan.dto.response;

import com.enigma.loan.entity.LoanType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoanTypeResponse {
    private String id;
    private String type;
    private Double maxLoan;

    public LoanType toEntity(){
        return LoanType.builder()
                .id(id)
                .type(type)
                .maxLoan(maxLoan)
                .build();
    }
}
