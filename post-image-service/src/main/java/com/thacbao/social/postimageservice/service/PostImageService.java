package com.thacbao.social.postimageservice.service.impl;

import com.thacbao.social.postimageservice.dto.request.PostImageRequest;
import com.thacbao.social.postimageservice.entity.PostImage;

import java.util.List;

public interface PostImageService {
    PostImage insertImage(Long postId, PostImageRequest request);
    List<PostImage> getAllPostImage(Long postId);
}
