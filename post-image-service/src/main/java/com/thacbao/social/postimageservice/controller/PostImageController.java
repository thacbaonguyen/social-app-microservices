package com.thacbao.social.postimageservice.controller;

import com.thacbao.social.postimageservice.dto.request.PostImageRequest;
import com.thacbao.social.postimageservice.entity.Post;
import com.thacbao.social.postimageservice.entity.PostImage;
import com.thacbao.social.postimageservice.entity.User;
import com.thacbao.social.postimageservice.service.impl.PostImageService;
import com.thacbao.social.postimageservice.service.impl.PostService;
import com.thacbao.social.postimageservice.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/posts")
public class PostImageController {
    private final PostService postService;
    private final UserService userService;
    private final PostImageService postImageService;
    //, consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    @PostMapping( value = "/upload/{id}")
    public ResponseEntity<?> insertPostImage(@PathVariable("id") Long postId,
                                             @RequestHeader("Authorization") String jwt,
                                             @ModelAttribute("files")List<MultipartFile> files
                                             ){
        if (!postService.existPost(postId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy bài đăng");
        }
        User user = userService.getMyInfo(jwt);
        Post post = postService.getPostById(postId);
        if (user.getId() != post.getUserId()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Không có quyền truy cập và chỉnh sửa bài đăng này");
        }
        try {
            files = files == null ? new ArrayList<>() : files;
            if (files.size() > 10) {
                return ResponseEntity.badRequest().body("Bạn chỉ có thể tải lên tối đa 10 ảnh");
            }
            List<PostImage> list = new ArrayList<>();
            for (MultipartFile file : files){
                if (file.getSize() == 0) {
                    continue;
                }
                if (file.getSize() > 10 * 1024 * 1024){
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File quá kích cỡ cho phép");
                }
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")){
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("File không đúng định dạng cho phép");
                }
                String fileName = storeFile(file);
                PostImage postImage = postImageService.insertImage(postId, PostImageRequest.builder()
                                .url(fileName)
                        .build()
                );
                list.add(postImage);
            }
            return ResponseEntity.ok(list);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    private String storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
        Path uploadDir = Paths.get("uploads-post");
        if (!Files.exists(uploadDir)){
            Files.createDirectories(uploadDir);
        }
        Path destination = Paths.get(uploadDir.toString(), uniqueFileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }
    @GetMapping("/image/{id}")
    public ResponseEntity<?> getAllPostImage(@PathVariable("id") Long postId){
        return ResponseEntity.status(HttpStatus.OK).body(postImageService.getAllPostImage(postId));
    }
}
