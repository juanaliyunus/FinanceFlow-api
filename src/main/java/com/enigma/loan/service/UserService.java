package com.enigma.loan.service;

import com.enigma.loan.dto.response.UserResponse;
import com.enigma.loan.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    User loadUserById(String userId);
    User getByContext();
    UserResponse getUserById(String id);
}
