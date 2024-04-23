package com.thacbao.social.hashtagservice.controller;

import com.thacbao.social.hashtagservice.dto.request.HashtagRequest;
import com.thacbao.social.hashtagservice.entity.Hashtag;
import com.thacbao.social.hashtagservice.entity.Post;
import com.thacbao.social.hashtagservice.entity.User;
import com.thacbao.social.hashtagservice.service.HashtagService;
import com.thacbao.social.hashtagservice.service.PostService;
import com.thacbao.social.hashtagservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/posts")
public class HashtagController {
    private final PostService postService;
    private final UserService userService;
    private final HashtagService hashtagService;
    @PostMapping("/hashtag/{id}")
    public ResponseEntity<?> insertHashtag(@PathVariable("id") Long postId,
                                           @RequestHeader("Authorization") String jwt,
                                           @RequestBody HashtagRequest request){
        if (!postService.existPost(postId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy bài đăng");
        }
        User user = userService.getMyInfo(jwt);
        Post post = postService.getPostById(postId);
        if (user.getId() != post.getUserId()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Không có quyền truy cập bài đăng này");
        }
        String hashtag = hashtagService.insertHashtag(postId, request);
        return ResponseEntity.status(HttpStatus.OK).body(hashtag);
    }
    @GetMapping("/hashtag/{id}")
    public ResponseEntity<?> getHashTag(@PathVariable("id") Long postId){
        return ResponseEntity.status(HttpStatus.OK).body(hashtagService.getHashtag(postId));
    }
    @GetMapping("/hashtag/find")
    public ResponseEntity<?> getPostId(@RequestParam String hashtag){
        return ResponseEntity.status(HttpStatus.OK).body(hashtagService.getPostId(hashtag));
    }
}
