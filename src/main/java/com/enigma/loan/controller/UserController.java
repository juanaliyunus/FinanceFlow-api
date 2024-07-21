package com.enigma.loan.controller;

import com.enigma.loan.constant.APIUrl;
import com.enigma.loan.dto.response.CommonResponse;
import com.enigma.loan.dto.response.UserResponse;
import com.enigma.loan.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = APIUrl.USER_API)
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<UserResponse>> getUserById(@PathVariable String id) {
        UserResponse response = userService.getUserById(id);
        CommonResponse<UserResponse> commonResponse = CommonResponse.<UserResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success get data")
                .data(response)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }
}
