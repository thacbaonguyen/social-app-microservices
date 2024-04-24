package com.thacbao.social.likeservice.service.impl;

import com.thacbao.social.likeservice.entity.LikeComment;
import com.thacbao.social.likeservice.entity.LikePost;
import com.thacbao.social.likeservice.exception.DataNotFoundException;
import com.thacbao.social.likeservice.repository.LikeCommentRepository;
import com.thacbao.social.likeservice.repository.LikePostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService implements com.thacbao.social.likeservice.service.LikeService {
    private final LikePostRepository likePostRepository;
    private final LikeCommentRepository likeCommentRepository;
    @Override
    public String insertLikePost(Long postId, Long userId) {
        LikePost likePost = LikePost.builder()
                .postId(postId)
                .userId(userId)
                .build();
        likePostRepository.save(likePost);
        return "Liked post";
    }
    @Override
    public String insertLikeComment(Long commentId, Long userId) {
        LikeComment likeComment = LikeComment.builder()
                .commentId(commentId)
                .userId(userId)
                .build();
        likeCommentRepository.save(likeComment);
        return "Liked comment";
    }

    @Override
    public String disLikePost(Long postId, Long userId) {
        LikePost likePost = likePostRepository.findByPostIdAndUserId(postId, userId).orElseThrow(() ->
                new DataNotFoundException("Khong tim thay bai dang"));
        likePostRepository.delete(likePost);
        return "ok";
    }

    @Override
    public String disLikeComment(Long commentId, Long userId) {
        LikeComment likeComment = likeCommentRepository.findByCommentIdAndUserId(commentId, userId).orElseThrow(() ->
                new DataNotFoundException("Khong tim thay bai dang"));
        likeCommentRepository.delete(likeComment);
        return "ok";
    }

    @Override
    public Long countLikePost(Long postId) {
        return likePostRepository.countLikePost(postId);
    }

    @Override
    public Long countLikeComment(Long commentId) {
        return likeCommentRepository.countLikeComment(commentId);
    }

    @Override
    public List<Long> likePostArchive(Long userId) {
        List<LikePost> list = likePostRepository.findByUserId(userId);
        List<Long> result = new ArrayList<>();
        for (LikePost item : list){
            result.add(item.getPostId());
        }
        return result;
    }

    @Override
    public List<Long> likeCommentArchive(Long userId) {
        List<LikeComment> list = likeCommentRepository.findByUserId(userId);
        List<Long> result = new ArrayList<>();
        for (LikeComment item : list){
            result.add(item.getCommentId());
        }
        return result;
    }
}
