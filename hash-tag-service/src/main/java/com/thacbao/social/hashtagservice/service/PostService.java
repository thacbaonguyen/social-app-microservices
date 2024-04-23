package com.thacbao.social.hashtagservice.service;

import com.thacbao.social.hashtagservice.entity.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "post-service", url = "http://localhost:5001")
public interface PostService {
    @GetMapping("api/v1/posts/exist/{id}")
    public boolean existPost(@PathVariable("id") Long postId);
    @GetMapping("api/v1/posts/{id}")
    public Post getPostById(@PathVariable Long id);
}
