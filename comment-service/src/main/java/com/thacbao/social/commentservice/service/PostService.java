package com.thacbao.social.commentservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "post-service", url = "http://localhost:5001")
public interface PostService {
    @GetMapping("api/v1/posts/exist/{id}")
    public boolean existPost(@PathVariable("id") Long postId);
}