package com.thacbao.social.postimageservice.service.impl.impl;

import com.thacbao.social.postimageservice.dto.request.PostImageRequest;
import com.thacbao.social.postimageservice.entity.PostImage;
import com.thacbao.social.postimageservice.repository.PostImageRepository;
import com.thacbao.social.postimageservice.service.impl.PostImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostImageServiceImpl implements PostImageService {
    private final PostImageRepository postImageRepository;
    @Override
    public PostImage insertImage(Long postId, PostImageRequest request) {
        PostImage postImage = PostImage.builder()
                .url(request.getUrl())
                .postId(postId)
                .build();
        postImageRepository.save(postImage);
        return postImage;
    }

    @Override
    public List<PostImage> getAllPostImage(Long postId) {
        return postImageRepository.findByPostId(postId);
    }
}
