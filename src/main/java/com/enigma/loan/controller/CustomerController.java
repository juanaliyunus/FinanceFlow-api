package com.enigma.loan.controller;

import com.enigma.loan.constant.APIUrl;
import com.enigma.loan.dto.request.CustomerRequest;
import com.enigma.loan.dto.response.CommonResponse;
import com.enigma.loan.dto.response.CustomerResponse;
import com.enigma.loan.dto.response.ProfilePictureResponse;
import com.enigma.loan.service.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping(path = APIUrl.CUSTOMERS_API)
@SecurityRequirement(name = "Bearer Authentication")
public class CustomerController {
    private final CustomerService customerService;

    @PutMapping("/{customerId}/upload/avatar")
    public ResponseEntity<CommonResponse<ProfilePictureResponse>> uploadAvatar(@PathVariable String customerId, @RequestPart("avatar") MultipartFile file) {
        ProfilePictureResponse response = customerService.uploadAvatar(customerId, file);
        CommonResponse<ProfilePictureResponse> commonResponse = CommonResponse.<ProfilePictureResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Profile picture successfully uploaded")
                .data(response)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> getCustomer(@PathVariable String id) {
        CustomerResponse response = customerService.getCustomerById(id);
        CommonResponse<CustomerResponse> commonResponse = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success get customer by id")
                .data(response)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<CustomerResponse>>> getAllCustomer() {
        List<CustomerResponse> response = customerService.getAllCustomer();
        CommonResponse<List<CustomerResponse>> commonResponse = CommonResponse.<List<CustomerResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success get all data")
                .data(response)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<CustomerResponse>> updateCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse response = customerService.updateCustomer(customerRequest);
        CommonResponse<CustomerResponse> commonResponse = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success update data")
                .data(response)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    //Soft Delete
    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<String>> softDeleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        CommonResponse<String> commonResponse = CommonResponse.<String>builder()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .message("Success delete data")
                .data(null)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }


}
