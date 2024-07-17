package com.enigma.loan.service;

import com.enigma.loan.model.dto.request.InstalmentTypeRequest;
import com.enigma.loan.model.dto.response.InstalmentTypeResponse;
import com.enigma.loan.model.entity.InstalmentType;

import java.util.List;

public interface InstalmentTypeService {
        InstalmentTypeResponse createInstalmentType(InstalmentTypeRequest instalmentTypeRequest);
        InstalmentTypeResponse getInstalmentTypeById(String instalmentTypeId);
        List<InstalmentTypeResponse> getAllInstalmentTypes();
        InstalmentTypeResponse updateInstalmentType(InstalmentTypeRequest instalmentTypeRequest);
        void deleteInstalmentType(String instalmentTypeId);

        InstalmentType getById(String id);
}
