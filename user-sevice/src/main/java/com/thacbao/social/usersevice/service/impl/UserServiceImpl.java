package com.thacbao.social.usersevice.service.impl;

import com.thacbao.social.usersevice.Jwt.GenerateToken;
import com.thacbao.social.usersevice.annotation.ValidPassword;
import com.thacbao.social.usersevice.dto.request.LoginRequest;
import com.thacbao.social.usersevice.dto.request.UserRequest;
import com.thacbao.social.usersevice.dto.response.UserLoginResponse;
import com.thacbao.social.usersevice.entity.User;
import com.thacbao.social.usersevice.enums.Role;
import com.thacbao.social.usersevice.exception.*;
import com.thacbao.social.usersevice.repository.UserRepository;
import com.thacbao.social.usersevice.service.UserService;
import com.thacbao.social.usersevice.utils.EmailUtil;
import com.thacbao.social.usersevice.utils.OtpUtil;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class UserServiceImpl implements UserService {
//    final UserMapper userMapper;
    final ModelMapper modelMapper;
    final OtpUtil otpUtil;
    final EmailUtil emailUtil;
    private ErrorCode errorCode;
    final PasswordEncoder passwordEncoder;
    final GenerateToken generate;

    final UserRepository userRepository;
    @Override
    public User createUser(UserRequest request) {
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(request.getEmail(), otp);
        } catch (MessagingException e) {
            log.info(e.getMessage());
            throw new RuntimeException("Gửi mã xác thực chưa thành công, vui lòng kiểm tra lại email của bạn");
        }
        String phoneNumber = request.getPhoneNumber();
        if(userRepository.existsByPhoneNumber(phoneNumber)){
            throw new DataNotFoundException("Phone number already exist");
        }
        if (userRepository.existsByEmail(request.getEmail())){
            throw new AlreadyExistingException("Email already exist");
        }
//        User user = userMapper.toUser(request);
        User user = modelMapper.map(request, User.class);
        user.setOtp(otp);
        user.setIsBlocked(false);
        user.setOtpGeneratedTime(LocalDateTime.now());
        user.setRole(Role.USER.name());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return user;
    }
    @Override
    public String verifyAccount(String email, String otp) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("Không tồn tại tài khoản của email này: " + email));
        if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (1 * 60)) {
            user.setIsActive(true);
            userRepository.save(user);
            return "Xác thực thành công, bạn đã có thể đăng nhập và sử dụng dịch vụ";
        }
        return "Vui lòng gửi lại mã xác thực và xác nhận";
    }

    @Override
    public String regenerateOtp(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Không tồn tại tài khoản của email này: " + email));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Không thể gửi mã, vui lòng thử lại");
        }
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return "Mã xác thực đã được gửi, vui lòng kiểm tra và xác thực trong vòng 1 phút";
    }
    @Override
    public User getInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepository.findByPhoneNumber(name).orElseThrow(() ->
                new DataNotFoundException("Cannot not existing"));
        return user;
    }

    @Override
    public String forgotPassword(String email) {
        String otp = otpUtil.generateOtp();
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new DataNotFoundException("Không tồn tại người dùng với email này"));
        try {
            emailUtil.sentSetPasswordEmail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Không thể gửi mã, vui lòng thử lại");
        }
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return "Vui lòng kiểm tra email của bạn để thay đổi mật khẩu";
    }

    @Override
    public User setPassword(String email, String otp, String newPassword) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new DataNotFoundException("User not exist"));
        if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (1 * 60)){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return user;
        }
        throw new InvalidAccountException("Vui lòng kiểm tra và thử lại");
    }

    @Override
    public UserLoginResponse login(LoginRequest request) {
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber()).orElseThrow(()->
                new DataNotFoundException("Không tìm thấy tài khoản"));
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!authenticated){
            throw new InvalidPasswordException("Mật khẩu không chính xác");
        }
        if (!user.getIsActive()){
            throw new InvalidAccountException("Tài khoản chưa được xác thực");
        }
        var token = generate.generateToken(request, Role.USER.name());
        return UserLoginResponse.builder()
                .token(token)
                .authenticated(authenticated)
                .build();
    }

    @Override
    public User updateUser(Long id, UserRequest request) {
        return null;
    }




}
