package com.thacbao.social.postservice.controller;

import com.thacbao.social.postservice.dto.request.PostRequest;
import com.thacbao.social.postservice.entity.Post;
import com.thacbao.social.postservice.entity.User;
import com.thacbao.social.postservice.service.PostService;
import com.thacbao.social.postservice.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/posts")
@RequiredArgsConstructor
public class PostController {
    private final UserService userService;

    private final PostService postService;
    private final ModelMapper modelMapper;
    @PostMapping()
    public ResponseEntity<?> createPost(@Valid @RequestBody PostRequest request,
                                        @RequestHeader("Authorization") String jwt){
        User user = userService.getMyInfo(jwt);
        Post post = postService.createPost(request, user.getRole(), user.getId());
        return ResponseEntity.ok(post);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id){
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }
    @GetMapping("/user/{user-id}")
    public ResponseEntity<?> getPostByUserId(@PathVariable("user-id") Long userId){
        List<Post> posts = postService.getPostByUser(userId);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/my-post")
    public ResponseEntity<?> getAllPost(@RequestHeader("Authorization") String jwt){
        User user = userService.getMyInfo(jwt);
        List<Post> posts = postService.getAllPost(user.getId());
        return ResponseEntity.ok(posts);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") Long postId,
                                        @RequestBody PostRequest request,
                                        @RequestHeader("Authorization") String jwt){
        User user = userService.getMyInfo(jwt);
        Post post = postService.updatePost(postId, request, user.getId());
        return ResponseEntity.ok(post);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long postId,
                                        @RequestHeader("Authorization") String jwt){
        User user = userService.getMyInfo(jwt);
        postService.delete(postId, user.getId());
        return ResponseEntity.ok("Delete post successfully");
    }
}
