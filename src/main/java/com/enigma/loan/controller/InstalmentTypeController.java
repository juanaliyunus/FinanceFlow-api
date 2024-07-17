package com.enigma.loan.controller;

import com.enigma.loan.constant.APIUrl;
import com.enigma.loan.model.dto.request.InstalmentTypeRequest;
import com.enigma.loan.model.dto.response.InstalmentTypeResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = APIUrl.INSTALMENT_TYPE_API)
public class InstalmentTypeController {
    public ResponseEntity<InstalmentTypeResponse> createInstalmentType(InstalmentTypeRequest instalmentTypeRequest) {
        return  null;
    }
    public ResponseEntity<InstalmentTypeResponse> getInstalmentType(String instalmentTypeId) {
        return null;
    }
    public  ResponseEntity<List<InstalmentTypeResponse>> getAllInstalmentType() {
        return null;
    }
    public ResponseEntity<InstalmentTypeResponse> updateInstalmentType(InstalmentTypeRequest instalmentTypeRequest) {
        return null;
    }

    public ResponseEntity<String> deleteInstalmentType(String instalmentTypeId) {
        return null;
    }

}
