package com.thacbao.social.postservice.service;

import com.thacbao.social.postservice.entity.PostImage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "post-image-service", url = "http://localhost:5002")
public interface PostImageService {
    @GetMapping("api/v1/posts/image/{id}")
    public List<PostImage> getAllPostImage(@PathVariable("id") Long postId);
}
