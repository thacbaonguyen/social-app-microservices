package com.thacbao.social.likeservice.repository;

import com.thacbao.social.likeservice.entity.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {
    Optional<LikeComment> findByCommentIdAndUserId(Long commentId, Long userId);

    @Query("SELECT COUNT(l) FROM LikeComment l WHERE l.commentId = :commentId")
    Long countLikeComment(Long commentId);

    List<LikeComment> findByUserId(Long userId);
}
