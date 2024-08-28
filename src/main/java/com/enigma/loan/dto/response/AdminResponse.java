package com.enigma.loan.dto.response;

import ch.qos.logback.core.model.processor.AllowAllModelFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {
    private String id;
    private String name;
}
