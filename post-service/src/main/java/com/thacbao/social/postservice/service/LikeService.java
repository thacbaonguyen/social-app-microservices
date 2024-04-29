package com.thacbao.social.postservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "like-service", url = "http://localhost:5004")
public interface LikeService {
    @GetMapping("api/v1/likes/post/like-count/{id}")
    public Long countLikePost(@PathVariable("id") Long postId);

    @GetMapping("api/v1/likes/post/like-archive/{id}")
    public List<Long> likePostArchive(@PathVariable("id") Long userId);
}
