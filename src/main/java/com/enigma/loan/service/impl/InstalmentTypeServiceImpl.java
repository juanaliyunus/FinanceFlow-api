package com.enigma.loan.service.impl;

import com.enigma.loan.dto.request.InstalmentTypeRequest;
import com.enigma.loan.dto.response.InstalmentTypeResponse;
import com.enigma.loan.entity.InstalmentType;
import com.enigma.loan.exception.ResourceNotFoundException;
import com.enigma.loan.repository.InstalmentTypeRepository;
import com.enigma.loan.service.InstalmentTypeService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstalmentTypeServiceImpl implements InstalmentTypeService {
    private final InstalmentTypeRepository instalmentTypeRepository;

    @Override
    public InstalmentType createInstalmentType(@NotNull InstalmentType instalmentTypeRequest) {
        if (instalmentTypeRequest.getInstalmentType() == null) {
            throw new IllegalArgumentException("Instalment type must not be null");
        }
        // Periksa keberadaan entitas berdasarkan instalmentType
        Optional<InstalmentType> optionalInstalmentType = instalmentTypeRepository.findByInstalmentType(instalmentTypeRequest.getInstalmentType());
        if (optionalInstalmentType.isPresent()) {
            // Jika entitas sudah ada, kembalikan entitas tersebut
            return optionalInstalmentType.get();
        }
        // Jika entitas tidak ada, simpan dan kembalikan entitas baru
        return instalmentTypeRepository.saveAndFlush(instalmentTypeRequest);
    }


    @Override
    public InstalmentTypeResponse getInstalmentTypeById(String instalmentTypeId) {
        InstalmentType instalmentType = instalmentTypeRepository.findById(instalmentTypeId).orElseThrow(() -> new ResourceNotFoundException("Instament type not found"));
        return InstalmentTypeResponse.builder()
                .id(instalmentType.getId())
                .instalmentType(instalmentType.getInstalmentType().name())
                .build();
    }

    @Override
    public List<InstalmentTypeResponse> getAllInstalmentTypes() {
        return instalmentTypeRepository.findAll().stream()
                .map(instalmentType -> InstalmentTypeResponse.builder()
                        .id(instalmentType.getId())
                        .instalmentType(instalmentType.getInstalmentType().name())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public InstalmentTypeResponse updateInstalmentType(@NotNull InstalmentTypeRequest instalmentTypeRequest) {
        InstalmentType instalmentType = instalmentTypeRepository.findById(instalmentTypeRequest.getId()).orElseThrow(() -> new ResourceNotFoundException("Instament type not found"));
        instalmentType.setInstalmentType(instalmentTypeRequest.getInstalmentType());

        InstalmentType updateInstalmentType = instalmentTypeRepository.saveAndFlush(instalmentType);

        return InstalmentTypeResponse.builder()
                .id(updateInstalmentType.getId())
                .instalmentType(updateInstalmentType.getInstalmentType().name())
                .build();
    }

    @Override
    public void deleteInstalmentType(String instalmentTypeId) {
        InstalmentType instalmentType = instalmentTypeRepository.findById(instalmentTypeId).orElseThrow(() -> new ResourceNotFoundException("Instament type not found"));
        instalmentTypeRepository.delete(instalmentType);
    }
}
