package com.enigma.loan.service;

import com.enigma.loan.dto.request.CustomerRequest;
import com.enigma.loan.dto.response.CustomerResponse;
import com.enigma.loan.dto.response.ProfilePictureResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerService {
    ProfilePictureResponse uploadAvatar(String id, MultipartFile file);

    CustomerResponse getCustomerById(String id);
    List<CustomerResponse> getAllCustomer();
    CustomerResponse updateCustomer(CustomerRequest customerRequest);
    //    Soft delete
    void deleteCustomer(String id);

}
