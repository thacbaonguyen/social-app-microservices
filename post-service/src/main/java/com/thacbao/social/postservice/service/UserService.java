package com.thacbao.social.postservice.service;

import com.thacbao.social.postservice.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@FeignClient(name = "user-service", url = "http://localhost:5000")
public interface UserService {
    @GetMapping("api/v1/users/my-info")
    public User getMyInfo(@RequestHeader("Authorization") String jwt);
}
