package com.thacbao.social.usersevice.dto.response;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginResponse {
    private String token;
    private boolean authenticated;
}
