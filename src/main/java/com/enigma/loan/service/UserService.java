package com.enigma.loan.service;

import com.enigma.loan.model.dto.request.UserRequest;
import com.enigma.loan.model.dto.response.UserResponse;
import com.enigma.loan.model.entity.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(UserRequest userRequest);
    UserResponse getUserById(String id);
    List<UserResponse> getAllUsers();
    void deleteUser(String id);

    User findUserById(String id);
}
