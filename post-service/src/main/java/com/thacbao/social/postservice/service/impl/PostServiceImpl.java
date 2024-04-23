package com.thacbao.social.postservice.service.impl;

import com.thacbao.social.postservice.dto.request.PostRequest;
import com.thacbao.social.postservice.dto.response.PostResponse;
import com.thacbao.social.postservice.entity.Post;
import com.thacbao.social.postservice.entity.PostImage;
import com.thacbao.social.postservice.exception.DataNotFoundException;
import com.thacbao.social.postservice.exception.PermissionException;
import com.thacbao.social.postservice.repository.PostRepository;
import com.thacbao.social.postservice.service.PostImageService;
import com.thacbao.social.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final PostImageService postImageService;
    @Override
    public Post createPost(PostRequest request, String role, Long userId) {
        Post post = modelMapper.map(request, Post.class);
        post.setLikeCount(0L);
        post.setCommentCount(0L);
        post.setShareCount(0L);
        post.setSaveCount(0L);
        post.setUserId(userId);
        postRepository.save(post);
        return post;
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).
                orElseThrow(() ->  new DataNotFoundException("Không tìm thấy bài viết"));
    }

    @Override
    public List<PostResponse> getPostByUser(Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        if (posts.isEmpty()){
            throw new DataNotFoundException("Không tìm thấy bài viết nào hoặc người dùng không tồn tại");
        }
        List<PostResponse> postResponses = new ArrayList<>();
        for (Post item : posts){
            PostResponse postResponse = new PostResponse();
            List<PostImage> postImageList = postImageService.getAllPostImage(item.getId());
            modelMapper.map(item, postResponse);
            postResponse.setPostImageList(postImageList);
            postResponses.add(postResponse);
        }
        return postResponses;
    }

    @Override
    public List<PostResponse> getAllPost(Long userId) {

        List<Post> posts = postRepository.findByUserId(userId);
        List<PostResponse> postResponses = new ArrayList<>();
        for (Post item : posts){
            PostResponse postResponse = new PostResponse();
            List<PostImage> postImageList = postImageService.getAllPostImage(item.getId());
            modelMapper.map(item, postResponse);
            postResponse.setPostImageList(postImageList);
            postResponses.add(postResponse);
        }
        return postResponses;
    }

    @Override
    public Post updatePost(Long postId, PostRequest request, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new DataNotFoundException("Không tìm thấy bài viết"));
        if (post.getUserId() != userId){
            throw new PermissionException("Bạn không có quyền cập nhật bài viết này");
        }
        modelMapper.map(request, post);
        postRepository.save(post);
        return post;
    }

    @Override
    public void delete(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new DataNotFoundException("Không tìm thấy bài viết"));
        if (post.getUserId() != userId){
            throw new PermissionException("Bạn không có quyền thực hiện xóa bài viết này");
        }
        postRepository.deleteById(postId);
    }

    @Override
    public boolean existPost(Long postId) {
        return postRepository.existsById(postId);
    }
}