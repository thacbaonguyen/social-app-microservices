package com.thacbao.social.commentservice.controller;

import com.thacbao.social.commentservice.dto.request.CommentRequest;
import com.thacbao.social.commentservice.entity.User;
import com.thacbao.social.commentservice.service.CommentService;
import com.thacbao.social.commentservice.service.PostService;
import com.thacbao.social.commentservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/post/comments")
public class CommentController {
    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;
    @PostMapping("/insert/{id}")
    public ResponseEntity<?> insertPostComment(@PathVariable("id") Long postId,
                                               @RequestHeader("Authorization") String jwt,
                                               @RequestBody CommentRequest request){
        User user = userService.getMyInfo(jwt);
        if (!postService.existPost(postId)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Khong ton tai bai viet");
        }
        String message = commentService.insertComment(postId, user.getId(), request);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    @PostMapping("/reply/{id}")
    public ResponseEntity<?> insertReplyComment(@PathVariable("id") Long commentId,
                                                @RequestHeader("Authorization") String jwt,
                                                @RequestBody CommentRequest request){
        User user = userService.getMyInfo(jwt);
        String message = commentService.insertReplyComment(commentId, user.getId(), request);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAllCommentByPost(@PathVariable("id") Long postId){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentByPost(postId));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") Long commentId,
                                           @RequestHeader("Authorization") String jwt,
                                           @RequestBody CommentRequest request){
        User user = userService.getMyInfo(jwt);
        String message = commentService.updateComment(commentId, user.getId(), request);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long commentId,
                                           @RequestHeader("Authorization") String jwt){
        User user = userService.getMyInfo(jwt);
        String message = commentService.deleteComment(commentId, user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
