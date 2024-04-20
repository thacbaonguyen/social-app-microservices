package com.thacbao.social.postservice.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
