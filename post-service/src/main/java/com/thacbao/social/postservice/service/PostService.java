package com.thacbao.social.postservice.service;

import com.thacbao.social.postservice.dto.request.PostRequest;
import com.thacbao.social.postservice.entity.Post;

import java.util.List;

public interface PostService {
    Post createPost(PostRequest request, String role, Long userId);

    Post getPostById(Long id);

    List<Post> getPostByUser(Long userId);

    List<Post> getAllPost(Long userId);

    Post updatePost(Long postId, PostRequest request, Long userId);

    void delete(Long postId, Long userId);
}
