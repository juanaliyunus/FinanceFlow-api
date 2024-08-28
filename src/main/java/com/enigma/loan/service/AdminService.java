package com.enigma.loan.service;

import com.enigma.loan.entity.Admin;

import java.util.List;

public interface AdminService {
    Admin getAdminById(String id);
    List<Admin> getAllAdmins();
}
