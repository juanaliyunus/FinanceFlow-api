package com.enigma.loan.controller;

import com.enigma.loan.constant.APIUrl;
import com.enigma.loan.dto.request.LoanTypeRequest;
import com.enigma.loan.dto.response.CommonResponse;
import com.enigma.loan.dto.response.LoanTypeResponse;
import com.enigma.loan.service.LoanTypeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = APIUrl.LOAN_TYPE_API)
public class LoanTypeController {

    private final LoanTypeService loanTypeService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    @PostMapping
    public ResponseEntity<CommonResponse<LoanTypeResponse>> createLoanType(@RequestBody @Valid LoanTypeRequest loanTypeRequest) {
        LoanTypeResponse response = loanTypeService.createLoanType(loanTypeRequest);
        CommonResponse<LoanTypeResponse> commonResponse = CommonResponse.<LoanTypeResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Success create data")
                .data(response)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<LoanTypeResponse>>> getAllLoanTypes() {
        List<LoanTypeResponse> response = loanTypeService.getAllLoanTypes();
        CommonResponse<List<LoanTypeResponse>> commonResponse = CommonResponse.<List<LoanTypeResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success get all data")
                .data(response)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<LoanTypeResponse>> getLoanTypeById(@PathVariable String id) {
        LoanTypeResponse response = loanTypeService.getLoanTypeById(id);
        CommonResponse<LoanTypeResponse> commonResponse = CommonResponse.<LoanTypeResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success get data by id")
                .data(response)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<String>> deleteLoanTypeById(@PathVariable String id) {
        loanTypeService.deleteLoanTypeById(id);
        CommonResponse<String> commonResponse = CommonResponse.<String>builder()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .message("Success delete data")
                .data(null)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    @PutMapping
    public ResponseEntity<CommonResponse<LoanTypeResponse>> updateLoanType(@RequestBody @Valid LoanTypeRequest loanTypeRequest) {
        LoanTypeResponse response = loanTypeService.updateLoanType(loanTypeRequest);
        CommonResponse<LoanTypeResponse> commonResponse = CommonResponse.<LoanTypeResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success update data")
                .data(response)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }
}
