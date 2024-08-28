package com.enigma.loan.dto.response;

import com.enigma.loan.entity.InstalmentType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InstalmentTypeResponse {
    private String id;
    private String instalmentType;

    public InstalmentType toInstalmentTypeEntity() {
        return InstalmentType.builder()
                .id(id)
                .instalmentType(InstalmentType.EInstalmentType.valueOf(instalmentType))
                .build();
    }
}
