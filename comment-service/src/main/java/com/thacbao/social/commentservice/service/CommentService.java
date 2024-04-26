package com.thacbao.social.commentservice.service;

import com.thacbao.social.commentservice.dto.request.CommentRequest;
import com.thacbao.social.commentservice.dto.response.CommentResponse;
import com.thacbao.social.commentservice.entity.Comment;

import java.util.List;

public interface CommentService {
    String insertComment(Long postId, Long userId, CommentRequest request);
    String insertReplyComment(Long commentId, Long userId, CommentRequest request);

    List<CommentResponse> getAllCommentByPost(Long postId);

    String updateComment(Long commentId, Long userId, CommentRequest request);

    String deleteComment(Long commentId, Long userId);


}
