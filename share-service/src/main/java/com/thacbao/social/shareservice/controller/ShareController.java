package com.thacbao.social.shareservice.controller;

import com.thacbao.social.shareservice.dto.request.ShareRequest;
import com.thacbao.social.shareservice.entity.User;
import com.thacbao.social.shareservice.service.ShareService;
import com.thacbao.social.shareservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/post/share")
public class ShareController {
    private final UserService userService;
    private final ShareService shareService;
    @PostMapping("/{id}")
    public ResponseEntity<?> sharePost(@PathVariable("id") Long postId,
                                       @RequestHeader("Authorization") String jwt,
                                       @RequestBody ShareRequest request){
        User user = userService.getMyInfo(jwt);
        String result = shareService.sharePost(user.getId(), postId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getAllPostShareByUserId(@PathVariable("id") Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(shareService.getShareByUserId(userId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAllPostShareByPostId(@PathVariable("id") Long postId){
        return ResponseEntity.status(HttpStatus.OK).body(shareService.getShareByUserId(postId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSharePost(@PathVariable("id") Long shareId,
                                             @RequestHeader("Authorization") String jwt,
                                             @RequestBody ShareRequest request){
        User user = userService.getMyInfo(jwt);
        String result = shareService.updateSharePost(user.getId(), shareId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSharePost(@PathVariable("id") Long id,
                                             @RequestHeader("Authorization") String jwt){
        User user = userService.getMyInfo(jwt);
        return ResponseEntity.status(HttpStatus.OK).body(shareService.deleteSharePost(user.getId(), id));
    }
}
