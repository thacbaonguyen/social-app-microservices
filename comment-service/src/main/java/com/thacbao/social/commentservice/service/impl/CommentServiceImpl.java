package com.thacbao.social.commentservice.service.impl;

import com.thacbao.social.commentservice.dto.request.CommentRequest;
import com.thacbao.social.commentservice.dto.response.CommentResponse;
import com.thacbao.social.commentservice.entity.Comment;
import com.thacbao.social.commentservice.entity.EditHistory;
import com.thacbao.social.commentservice.exception.DataNotFoundException;
import com.thacbao.social.commentservice.exception.InvalidAccountException;
import com.thacbao.social.commentservice.repository.CommentRepository;
import com.thacbao.social.commentservice.repository.EditHistoryRepository;
import com.thacbao.social.commentservice.service.CommentService;
import com.thacbao.social.commentservice.service.LikeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final EditHistoryRepository editHistoryRepository;
    private final ModelMapper modelMapper;
    private final LikeService likeService;
    @Override
    public String insertComment(Long postId, Long userId, CommentRequest request) {
        Comment comment = Comment.builder()
                .content(request.getContent())
                .postId(postId)
                .userId(userId)
                .build();
        commentRepository.save(comment);
        return "Them thanh cong comment";
    }

    @Override
    public String insertReplyComment(Long commentId, Long userId, CommentRequest request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new DataNotFoundException("Khong tim thay binh luan"));
        Comment replyComment = Comment.builder()
                .userId(userId)
                .commentReply(comment.getId())
                .postId(comment.getPostId())
                .content(request.getContent())
                .build();
        commentRepository.save(replyComment);
        return "Them thanh cong reply comment";
    }

    @Override
    public List<CommentResponse> getAllCommentByPost(Long postId) {
        List<CommentResponse> responses = commentRepository.findByPostId(postId).stream()
                .map(comment -> {
                    CommentResponse commentResponse = modelMapper.map(comment, CommentResponse.class);
                    Long count = likeService.countLikeComment(comment.getId());
                    commentResponse.setLikeCount(count);
                    return commentResponse;
                }).collect(Collectors.toList());
        return responses;
    }

    @Override
    public String updateComment(Long commentId, Long userId, CommentRequest request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new DataNotFoundException("khong tim thay binh luan"));
        if (comment.getUserId() != userId){
            throw new InvalidAccountException("Khong duoc chinh sua binh luan nay");
        }
        EditHistory editHistory = EditHistory.builder()
                .content(comment.getContent())
                .commentId(comment.getId())
                .build();
        comment.setContent(request.getContent());
        commentRepository.save(comment);
        editHistoryRepository.save(editHistory);
        return "updated comment";
    }

    @Override
    public String deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new DataNotFoundException("khong tim thay binh luan"));
        if (comment.getUserId() != userId){
            throw new InvalidAccountException("Khong duoc xoa binh luan nay");
        }
        commentRepository.delete(comment);
        commentRepository.deleteByCommentReply(commentId);
        return "deleted comment";
    }
}
