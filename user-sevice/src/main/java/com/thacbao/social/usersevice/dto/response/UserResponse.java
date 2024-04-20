package com.thacbao.social.usersevice.dto.response;

import com.thacbao.social.usersevice.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse {
    Long id;
    String firstName;
    String lastName;
    String phoneNumber;
    String role;
}
