package com.enigma.loan.controller;

import com.enigma.loan.constant.APIUrl;
import com.enigma.loan.dto.request.auth.AuthenticationRequest;
import com.enigma.loan.dto.request.auth.NewAdminRequest;
import com.enigma.loan.dto.request.auth.NewCustomerRequest;
import com.enigma.loan.dto.request.auth.NewStaffRequest;
import com.enigma.loan.dto.response.CommonResponse;
import com.enigma.loan.dto.response.LoginResponse;
import com.enigma.loan.dto.response.RegisterResponse;
import com.enigma.loan.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = APIUrl.AUTH_API)
public class AuthenticationController {

private final AuthenticationService authenticationService;

    @PostMapping("/register/customer")
    public ResponseEntity<CommonResponse<RegisterResponse>> registerCustomer(@RequestBody NewCustomerRequest newCustomerRequest) {
        RegisterResponse response = authenticationService.registerCustomer(newCustomerRequest);
        CommonResponse<RegisterResponse> commonResponse = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully register new customer")
                .data(response)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<CommonResponse<RegisterResponse>> registerAdmin(@RequestBody NewAdminRequest newAdminRequest){
        RegisterResponse response = authenticationService.registerAdmin(newAdminRequest);
        CommonResponse<RegisterResponse> commonResponse = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully register new admin")
                .data(response)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @PostMapping("/register/staff")
    public ResponseEntity<CommonResponse<RegisterResponse>> registerStaff(@RequestBody NewStaffRequest newStaffRequest){
        RegisterResponse response = authenticationService.registerStaff(newStaffRequest);
        CommonResponse<RegisterResponse> commonResponse = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully register new staff")
                .data(response)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<LoginResponse>> login(@RequestBody AuthenticationRequest authenticationRequest) {
        LoginResponse response = authenticationService.login(authenticationRequest);
        CommonResponse<LoginResponse> commonResponse = CommonResponse.<LoginResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Success Login")
                .data(response)
                .build();
        return ResponseEntity.ok(commonResponse);
    }
}
