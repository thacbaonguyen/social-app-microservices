package com.thacbao.social.usersevice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thacbao.social.usersevice.annotation.ValidDateOfBirth;
import com.thacbao.social.usersevice.annotation.ValidPassword;
import com.thacbao.social.usersevice.annotation.ValidPhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @Size(min = 2, message = "Tên đầu phải có nhiều hơn 2 ký tự")
    @JsonProperty("first_name")
    String firstName;

    @Size(min = 2, message = "Tên cuối phải có nhiều hơn 2 ký tự")
    @JsonProperty("last_name")
    String lastName;

    @ValidPhoneNumber(message = "Số điện thoại không hợp lệ")
    @JsonProperty("phone_number")
    String phoneNumber;

    @Size(min = 6, max = 16, message = "Độ dài mật khẩu phải từ 6 đến 16 ký tự")
    @ValidPassword(message = "Mật khẩu phải chứa it nhất 1 ký tự viết hoa, 1 chữ số và không được bỏ trống")
    String password;

    @JsonProperty("retype_password")
    String retypePassword;

    @Email(message = "Email không hợp lệ")
    String email;

    @Max(value = 2, message = "Giới tính không hợp lệ")
    @Min(value = 0, message = "Giới tính không hợp lệ")
    Long gender;

    @ValidDateOfBirth(message = "Ngày tháng sinh của bạn không hợp lệ")
    LocalDate dob;

    String avatar;
}
