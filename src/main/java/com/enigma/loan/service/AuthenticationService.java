package com.enigma.loan.service;

import com.enigma.loan.dto.request.auth.AuthenticationRequest;
import com.enigma.loan.dto.request.auth.NewAdminRequest;
import com.enigma.loan.dto.request.auth.NewCustomerRequest;
import com.enigma.loan.dto.request.auth.NewStaffRequest;
import com.enigma.loan.dto.response.LoginResponse;
import com.enigma.loan.dto.response.RegisterResponse;

public interface AuthenticationService {

    RegisterResponse registerCustomer(NewCustomerRequest newCustomerRequest);
    RegisterResponse registerAdmin(NewAdminRequest newAdminRequest);
    RegisterResponse registerStaff(NewStaffRequest newStaffRequest);

    LoginResponse login(AuthenticationRequest authenticationRequest);
}
