package com.thacbao.social.likeservice.controller;

import com.thacbao.social.likeservice.entity.User;
import com.thacbao.social.likeservice.service.LikeService;
import com.thacbao.social.likeservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/likes")
@RequiredArgsConstructor
public class LikeController {
    private final UserService userService;
    private final LikeService likeService;
    @PostMapping("/post/{id}")
    public String insertLikePost(@PathVariable("id") Long postId,
                                            @RequestHeader("Authorization") String jwt){
        User user = userService.getMyInfo(jwt);
        return likeService.insertLikePost(postId, user.getId());
    }
    @PostMapping("/comment/{id}")
    public String insertLikeComment(@PathVariable("id") Long commentId,
                                 @RequestHeader("Authorization") String jwt){
        User user = userService.getMyInfo(jwt);
        return likeService.insertLikeComment(commentId, user.getId());
    }
    @DeleteMapping("/post/{id}")
    public String disLikePost(@PathVariable("id") Long postId,
                              @RequestHeader("Authorization") String jwt){
        User user = userService.getMyInfo(jwt);
        return likeService.disLikePost(postId, user.getId());
    }
    @DeleteMapping("/comment/{id}")
    public String disLikeComment(@PathVariable("id") Long commentId,
                                 @RequestHeader("Authorization") String jwt){
        User user = userService.getMyInfo(jwt);
        return likeService.disLikeComment(commentId, user.getId());
    }
    @GetMapping("/post/like-count/{id}")
    public Long countLikePost(@PathVariable("id") Long postId){
        return likeService.countLikePost(postId);
    }
    @GetMapping("/comment/like-count/{id}")
    public Long countLikeComment(@PathVariable("id") Long commentId){
        return likeService.countLikeComment(commentId);
    }
    @GetMapping("/post/like-archive/{id}")
    public List<Long> likePostArchive(@PathVariable("id") Long userId){
        return likeService.likePostArchive(userId);
    }
    @GetMapping("/comment/like-archive/{id}")
    public List<Long> likeCommentArchive(@PathVariable("id") Long userId){
        return likeService.likeCommentArchive(userId);
    }
}
