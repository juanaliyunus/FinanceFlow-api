package com.enigma.loan.service.impl;

import com.enigma.loan.dto.request.auth.AuthenticationRequest;
import com.enigma.loan.dto.request.auth.NewAdminRequest;
import com.enigma.loan.dto.request.auth.NewCustomerRequest;
import com.enigma.loan.dto.request.auth.NewStaffRequest;
import com.enigma.loan.dto.response.LoginResponse;
import com.enigma.loan.dto.response.RegisterResponse;
import com.enigma.loan.entity.*;
import com.enigma.loan.repository.AdminRepository;
import com.enigma.loan.repository.CustomerRepository;
import com.enigma.loan.repository.StaffRepository;
import com.enigma.loan.repository.UserRepository;
import com.enigma.loan.service.AuthenticationService;
import com.enigma.loan.service.JWTService;
import com.enigma.loan.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationServiceImpl implements AuthenticationService {
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;
    private final StaffRepository staffRepository;
    private final  JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse registerCustomer(NewCustomerRequest newCustomerRequest) {
        Role role = roleService.getOrSave(Role.ERole.ROLE_CUSTOMER);
        String hashPassword = passwordEncoder.encode(newCustomerRequest.getPassword());

        User user = User.builder()
                .username(newCustomerRequest.getUsername())
                .password(hashPassword)
                .roles(Collections.singleton(role))
                .build();
        userRepository.saveAndFlush(user);

        Customer customer = Customer.builder()
                .firstName(newCustomerRequest.getFirstName())
                .lastName(newCustomerRequest.getLastName())
                .dateOfBirth(newCustomerRequest.getDateOfBirth())
                .phone(newCustomerRequest.getPhone())
                .status(newCustomerRequest.getStatus())
                .user(user)
                .build();

        customerRepository.saveAndFlush(customer);

        return RegisterResponse.builder()
                .username(user.getUsername())
                .roles(role.getRoles())
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse registerAdmin(NewAdminRequest newAdminRequest) {
        Role role = roleService.getOrSave(Role.ERole.ROLE_ADMIN);
        String hashPassword = passwordEncoder.encode(newAdminRequest.getPassword());

        User user = User.builder()
                .username(newAdminRequest.getUsername())
                .password(hashPassword)
                .roles(Collections.singleton(role))
                .build();

        userRepository.saveAndFlush(user);

        Admin admin = Admin.builder()
                .name(newAdminRequest.getName())
                .email(newAdminRequest.getEmail())
                .user(user)
                .build();

        adminRepository.saveAndFlush(admin);

        return RegisterResponse.builder()
                .username(user.getUsername())
                .roles(role.getRoles())
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse registerStaff(NewStaffRequest newStaffRequest) {
        Role role = roleService.getOrSave(Role.ERole.ROLE_STAFF);
        String hashPassword = passwordEncoder.encode(newStaffRequest.getPassword());

        User user = User.builder()
                .username(newStaffRequest.getUsername())
                .password(hashPassword)
                .roles(Collections.singleton(role))
                .build();

        userRepository.saveAndFlush(user);

        Staff staff = Staff.builder()
                .name(newStaffRequest.getName())
                .email(newStaffRequest.getEmail())
                .user(user)
                .build();

        staffRepository.saveAndFlush(staff);

        return RegisterResponse.builder()
                .username(user.getUsername())
                .roles(role.getRoles())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public LoginResponse login(AuthenticationRequest authenticationRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        User user = (User) authenticate.getPrincipal();
        String token = jwtService.generateToken(user);

        Set<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        return LoginResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(roles)
                .token(token)
                .build();
    }
}
