package com.enigma.loan.controller;

import com.enigma.loan.constant.APIUrl;
import com.enigma.loan.model.dto.request.CustomerRequest;
import com.enigma.loan.model.dto.response.CustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping(path = APIUrl.CUSTOMERS_API)
public class CustomerController {

    @GetMapping ("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable String customerId) {
        return null;
    }

    @GetMapping
    public ResponseEntity<CustomerResponse> getAllCustomer(@RequestBody CustomerRequest customerRequest) {
        return null;
    }

    @PutMapping
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody CustomerRequest customerRequest) {
        return null;
    }

    //Soft Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String customerId) {
        return null;
    }

    @PostMapping("/{customerId}/upload/avatar")
    public ResponseEntity<CustomerResponse> uploadAvatar(@RequestParam("avatar") MultipartFile file) {
        return null;
    }

}
