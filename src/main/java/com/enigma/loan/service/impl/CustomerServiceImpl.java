package com.enigma.loan.service.impl;

import com.enigma.loan.dto.request.CustomerRequest;
import com.enigma.loan.dto.response.CustomerResponse;
import com.enigma.loan.dto.response.ProfilePictureResponse;
import com.enigma.loan.entity.Customer;
import com.enigma.loan.entity.ProfilePicture;
import com.enigma.loan.exception.ResourceNotFoundException;
import com.enigma.loan.repository.CustomerRepository;
import com.enigma.loan.service.CustomerService;
import com.enigma.loan.service.ProfilePictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ProfilePictureService profilePictureService;

    @Override
    public ProfilePictureResponse uploadAvatar(String id, MultipartFile file) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with id " + id + " not found"));

        ProfilePicture profilePicture = profilePictureService.create(file);
        customer.setProfilePicture(profilePicture);
        customerRepository.saveAndFlush(customer);

        return ProfilePictureResponse.builder()
                .name(profilePicture.getName())
                .url(profilePicture.getUrl())
                .build();
    }

    @Override
    public CustomerResponse getCustomerById(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with id " + id + " not found"));

        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .dateOfBirth(customer.getDateOfBirth())
                .phone(customer.getPhone())
                .status(customer.getStatus())
                .build();
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> CustomerResponse.builder()
                        .id(customer.getId())
                        .firstName(customer.getFirstName())
                        .lastName(customer.getLastName())
                        .dateOfBirth(customer.getDateOfBirth())
                        .phone(customer.getPhone())
                        .status(customer.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse updateCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(customerRequest.getId()).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setDateOfBirth(customerRequest.getDateOfBirth());
        customer.setPhone(customerRequest.getPhone());
        customer.setStatus(customerRequest.getStatus());

        customerRepository.saveAndFlush(customer);

        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .dateOfBirth(customer.getDateOfBirth())
                .phone(customer.getPhone())
                .status(customer.getStatus())
                .build();
    }

    @Override
    public void deleteCustomer(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        customer.setDeleted(true);
        customerRepository.saveAndFlush(customer);
    }
}
