package com.thacbao.social.postservice.service;

import com.thacbao.social.postservice.entity.Hashtag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "hashtag-service", url = "http://localhost:5003")
public interface HashtagService {
    @GetMapping("api/v1/posts/hashtag/{id}")
    public List<Hashtag> getHashTag(@PathVariable("id") Long postId);

    @GetMapping("api/v1/posts/hashtag/find")
    public List<Long> getPostId(@RequestParam String hashtag);
}
