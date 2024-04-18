package com.thacbao.social.usersevice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    String email;

    String password;

    LocalDate dob;

    Long gender;

    @Column(name = "phone_number")
    String phoneNumber;

    String avatar;

    @Column(name = "active")
    Boolean isActive;

    @Column(name = "block")
    Boolean isBlocked;

    String role;

    String otp;

    @Column(name = "otp_generated_time")
    private LocalDateTime otpGeneratedTime;
}
