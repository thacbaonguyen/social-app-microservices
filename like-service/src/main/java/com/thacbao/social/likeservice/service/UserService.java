package com.thacbao.social.likeservice.service;

import com.thacbao.social.likeservice.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", url = "http://localhost:5000")
public interface UserService {
    @GetMapping("api/v1/users/my-info")
    public User getMyInfo(@RequestHeader("Authorization") String jwt);
}

