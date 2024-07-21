package com.enigma.loan.controller;

import com.enigma.loan.constant.APIUrl;
import com.enigma.loan.dto.response.AdminResponse;
import com.enigma.loan.dto.response.CommonResponse;
import com.enigma.loan.entity.Admin;
import com.enigma.loan.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = APIUrl.ADMIN_API)
public class AdminController {
    private final AdminService adminService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<AdminResponse>> getCustomer(@PathVariable String id) {
        Admin response = adminService.getAdminById(id);
        AdminResponse adminResponse = adminToAdminResponse(response);
        CommonResponse<AdminResponse> commonResponse = CommonResponse.<AdminResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success get data by id")
                .data(adminResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<CommonResponse<List<AdminResponse>>> getAllCustomer() {
        List<Admin> admins = adminService.getAllAdmins();
        List<AdminResponse> adminResponses = admins.stream()
                .map(this::adminToAdminResponse)
                .collect(Collectors.toList());
        CommonResponse<List<AdminResponse>> commonResponse = CommonResponse.<List<AdminResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success get all data")
                .data(adminResponses)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    private AdminResponse adminToAdminResponse(Admin admin) {
        return AdminResponse.builder()
                .id(admin.getId())
                .name(admin.getName())
                .build();
    }

}
