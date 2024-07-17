package com.enigma.loan.service;

import com.enigma.loan.model.dto.request.CustomerRequest;
import com.enigma.loan.model.dto.response.CustomerResponse;
import com.enigma.loan.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);
    CustomerResponse getCustomerById(String id);
    List<CustomerResponse> getAllCustomer();
    CustomerResponse updateCustomer(CustomerRequest request);
    //    Soft delete
    void deleteCustomer(String id);

    Customer getById(String id);

}
