package com.thacbao.social.usersevice.service;

import com.thacbao.social.usersevice.dto.request.LoginRequest;
import com.thacbao.social.usersevice.dto.request.UserRequest;
import com.thacbao.social.usersevice.dto.response.UserLoginResponse;
import com.thacbao.social.usersevice.entity.User;

public interface UserService {
    User createUser(UserRequest request);

    User updateUser(Long id, UserRequest request);

    User getInfo();
    public String verifyAccount(String email, String otp);
    public String regenerateOtp(String email);

    public String forgotPassword(String email);
    public User setPassword(String email, String otp, String newPassword);
    UserLoginResponse login(LoginRequest request);
}
