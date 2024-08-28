package com.enigma.loan.service.impl;

import com.enigma.loan.dto.response.UserResponse;
import com.enigma.loan.entity.User;
import com.enigma.loan.exception.ResourceNotFoundException;
import com.enigma.loan.repository.UserRepository;
import com.enigma.loan.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    @Override
    public User loadUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Id not found"));
    }

    @Override
    public User getByContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authentication.getPrincipal().toString())
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found"));
        Set<String> roleName = user.getRoles().stream()
                .map(role -> role.getRoles().name())
                .collect(Collectors.toSet());
        return UserResponse.builder()
                .email(user.getUsername())
                .roles(roleName)
                .build();
    }
}
