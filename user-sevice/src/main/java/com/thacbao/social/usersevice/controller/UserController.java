package com.thacbao.social.usersevice.controller;

import com.thacbao.social.usersevice.annotation.ValidPassword;
import com.thacbao.social.usersevice.dto.request.LoginRequest;
import com.thacbao.social.usersevice.dto.request.UserRequest;
import com.thacbao.social.usersevice.dto.response.ApiResponse;
import com.thacbao.social.usersevice.dto.response.UserLoginResponse;
import com.thacbao.social.usersevice.dto.response.UserResponse;
import com.thacbao.social.usersevice.entity.User;
import com.thacbao.social.usersevice.exception.AppException;
import com.thacbao.social.usersevice.exception.DataNotFoundException;
import com.thacbao.social.usersevice.mapper.UserMapper;
import com.thacbao.social.usersevice.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
    @Autowired
    private UserService userService;
//    private UserMapper userMapper;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest request, BindingResult result)  {
        if (result.hasErrors()){
            List<String> errorMessage = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(ApiResponse.builder()
                            .code(2001)
                            .message("Not success")
                            .result(errorMessage)
                    .build());
        }
        if (!request.getPassword().equals(request.getRetypePassword())){
            return ResponseEntity.badRequest().body("Mật khẩu không trùng khớp");
        }
        User user = null;
        try {
            user = userService.createUser(request);
        } catch (AppException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(ApiResponse.builder()
                        .code(2000)
                        .message("Success")
                        .result("Vui lòng kiểm tra và xác thực tài khoản trong hộp thư email của bạn")
                .build());
    }
    @PutMapping("/verify-account")
    public ResponseEntity<?> verifyAccount(@RequestParam String email,
                                                @RequestParam String otp) {
        return new ResponseEntity<>(ApiResponse.builder()
                .code(2000)
                .result(userService.verifyAccount(email, otp))
                .message("Success")
                .build(), HttpStatus.OK);
    }
    @PutMapping("/regenerate-otp")
    public ResponseEntity<?> regenerateOtp(@RequestParam String email) {
        return new ResponseEntity<>(ApiResponse.builder()
                .code(2000)
                .message("Success")
                .result(userService.regenerateOtp(email))
                .build(), HttpStatus.OK);
    }
    @GetMapping("/my-info")
    public ResponseEntity<?> getMyInfo(){
        try {
            User user = userService.getInfo();
            return ResponseEntity.ok(ApiResponse.builder()
                            .code(2000)
                            .result(modelMapper.map(user, UserResponse.class))
                            .message("Success")
                    .build());
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(ApiResponse.builder()
                            .code(2001)
                            .message("Not success")
                            .result(e.getMessage())
                    .build());
        }
    }
    @PutMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email){
        try {
            return ResponseEntity.ok(ApiResponse.builder()
                            .code(2000)
                            .result(userService.forgotPassword(email))
                            .message("Success")
                    .build());
        } catch (DataNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/set-password")
    public ResponseEntity<?> setPasswordEmail(@RequestParam String email,
                                              @RequestParam String otp,
                                              @RequestHeader @ValidPassword String newPassword ){
        try {
            User user = userService.setPassword(email,otp, newPassword);
            return ResponseEntity.ok(ApiResponse.builder()
                            .code(2000)
                            .message("Success")
                            .result(modelMapper.map(user, UserResponse.class))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.builder()
                            .code(2001)
                            .message("Not success")
                            .result(e.getMessage())
                    .build());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request){
        try {
            UserLoginResponse userLoginResponse = userService.login(request);
            return ResponseEntity.ok(ApiResponse.builder()
                            .code(2000)
                            .message("Success")
                            .result(userLoginResponse)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.builder()
                            .code(2001)
                            .message("NOt success")
                            .result(e.getMessage())
                    .build());
        }
    }
}
