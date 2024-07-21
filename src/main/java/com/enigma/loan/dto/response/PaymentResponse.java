package com.enigma.loan.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private String token;
    @JsonProperty("redirect_url")
    private String redirectUrl;
}
