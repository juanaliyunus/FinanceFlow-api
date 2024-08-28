package com.enigma.loan.service;

import com.enigma.loan.dto.request.InstalmentTypeRequest;
import com.enigma.loan.dto.response.InstalmentTypeResponse;
import com.enigma.loan.entity.InstalmentType;

import java.util.List;

public interface InstalmentTypeService {
        InstalmentType createInstalmentType(InstalmentType instalmentTypeRequest);
        InstalmentTypeResponse getInstalmentTypeById(String instalmentTypeId);
        List<InstalmentTypeResponse> getAllInstalmentTypes();
        InstalmentTypeResponse updateInstalmentType(InstalmentTypeRequest instalmentTypeRequest);
        void deleteInstalmentType(String instalmentTypeId);
}
