package com.enigma.loan.controller;

import com.enigma.loan.constant.APIUrl;
import com.enigma.loan.dto.request.InstalmentTypeRequest;
import com.enigma.loan.dto.response.CommonResponse;
import com.enigma.loan.dto.response.InstalmentTypeResponse;
import com.enigma.loan.entity.InstalmentType;
import com.enigma.loan.service.InstalmentTypeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = APIUrl.INSTALMENT_TYPE_API)
public class InstalmentTypeController {
    private final InstalmentTypeService instalmentTypeService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    @PostMapping
    public ResponseEntity<CommonResponse<InstalmentType>> createInstalmentType(@RequestBody InstalmentType instalmentTypeRequest) {
        InstalmentType response = instalmentTypeService.createInstalmentType(instalmentTypeRequest);
        CommonResponse<InstalmentType> commonResponse = CommonResponse.<InstalmentType>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Success create data")
                .data(response)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<InstalmentTypeResponse>> getInstalmentType(@PathVariable String id) {
        InstalmentTypeResponse response = instalmentTypeService.getInstalmentTypeById(id);
        CommonResponse<InstalmentTypeResponse> commonResponse = CommonResponse.<InstalmentTypeResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success create data")
                .data(response)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<InstalmentTypeResponse>>> getAllInstalmentType() {
        List<InstalmentTypeResponse> response = instalmentTypeService.getAllInstalmentTypes();
        CommonResponse<List<InstalmentTypeResponse>> commonResponse = CommonResponse.<List<InstalmentTypeResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success get all data")
                .data(response)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    @PutMapping
    public ResponseEntity<CommonResponse<InstalmentTypeResponse>> updateInstalmentType(@Valid @RequestBody InstalmentTypeRequest instalmentTypeRequest) {
        InstalmentTypeResponse response = instalmentTypeService.updateInstalmentType(instalmentTypeRequest);
        CommonResponse<InstalmentTypeResponse> commonResponse = CommonResponse.<InstalmentTypeResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success update data")
                .data(response)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<String>> deleteInstalmentType(@PathVariable String id) {
        instalmentTypeService.deleteInstalmentType(id);
        CommonResponse<String> commonResponse = CommonResponse.<String>builder()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .message("Success delete data")
                .data("null")
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

}
