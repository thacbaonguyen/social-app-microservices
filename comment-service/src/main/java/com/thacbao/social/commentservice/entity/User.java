package com.thacbao.social.commentservice.entity;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User{
    Long id;

    String firstName;

    String lastName;

    String email;

    String password;

    LocalDate dob;

    Long gender;

    String phoneNumber;

    String avatar;

    Boolean isActive;

    Boolean isBlocked;

    String role;

    String otp;

    private LocalDateTime otpGeneratedTime;
}