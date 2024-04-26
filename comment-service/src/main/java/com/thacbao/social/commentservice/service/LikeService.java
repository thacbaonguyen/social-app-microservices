package com.thacbao.social.commentservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "like-service", url = "http://localhost:5004")
public interface LikeService {
    @GetMapping("api/v1/likes/comment/like-count/{id}")
    public Long countLikeComment(@PathVariable("id") Long commentId);
}
