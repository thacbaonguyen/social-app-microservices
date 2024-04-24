package com.thacbao.social.likeservice.service;

import javax.xml.stream.events.Comment;
import java.util.List;

public interface LikeService {
    String insertLikePost(Long postId, Long userId);

    String insertLikeComment(Long commentId, Long userId);

    String disLikePost(Long postId, Long userId);

    String disLikeComment(Long commentId, Long userId);
    Long countLikePost(Long postId);

    Long countLikeComment(Long commentId);

    List<Long> likePostArchive(Long userId);

    List<Long> likeCommentArchive(Long userId);
}
