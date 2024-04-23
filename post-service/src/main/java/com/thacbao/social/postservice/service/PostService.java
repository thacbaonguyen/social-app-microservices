package com.thacbao.social.postservice.service;

import com.thacbao.social.postservice.dto.request.PostRequest;
import com.thacbao.social.postservice.dto.response.PostResponse;
import com.thacbao.social.postservice.entity.Post;

import java.util.List;

public interface PostService {
    Post createPost(PostRequest request, String role, Long userId);

    PostResponse getPostById(Long id);

    List<PostResponse> getPostByUser(Long userId);

    List<PostResponse> getAllPost(Long userId);

    List<PostResponse> getPostByHashtag(String hashtag);

    Post updatePost(Long postId, PostRequest request, Long userId);

    void delete(Long postId, Long userId);

    boolean existPost(Long postId);
}
