package com.thacbao.social.commentservice.repository;

import com.thacbao.social.commentservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteByCommentReply(Long commentReply);
    List<Comment> findByPostId(Long postId);
}
