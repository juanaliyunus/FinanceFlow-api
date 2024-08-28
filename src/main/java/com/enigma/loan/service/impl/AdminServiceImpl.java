package com.enigma.loan.service.impl;

import com.enigma.loan.entity.Admin;
import com.enigma.loan.entity.Customer;
import com.enigma.loan.exception.ResourceNotFoundException;
import com.enigma.loan.repository.AdminRepository;
import com.enigma.loan.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    @Override
    public Admin getAdminById(String id) {
        return adminRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Admin with id " + id + " not found"));
    }

    @Override
    public List<Admin> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins;
    }

}
