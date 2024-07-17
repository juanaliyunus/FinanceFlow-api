package com.enigma.loan.controller;

import com.enigma.loan.constant.APIUrl;
import com.enigma.loan.model.dto.request.UserRequest;
import com.enigma.loan.model.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = APIUrl.USER_API)
public class UserController {
    public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
        return null;
    }

    public ResponseEntity<UserResponse> getUserById(String id) {
        return null;
    }

    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return null;
    }

    public ResponseEntity<UserResponse> updateUser(UserRequest userRequest) {
        return null;
    }

    public ResponseEntity<String> deleteUser(String id) {
        return null;
    }

}
