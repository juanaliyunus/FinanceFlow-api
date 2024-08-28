package com.enigma.loan.dto.request;

import com.enigma.loan.entity.InstalmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstalmentTypeRequest {
    private String id;
    private InstalmentType.EInstalmentType instalmentType;
}
