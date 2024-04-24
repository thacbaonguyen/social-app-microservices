package com.thacbao.social.likeservice.repository;

import com.thacbao.social.likeservice.entity.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Long> {
    Optional<LikePost> findByPostIdAndUserId(Long postId, Long userId);
    @Query("SELECT COUNT(l) FROM LikePost l WHERE l.postId = :postId")
    Long countLikePost(Long postId);

    List<LikePost> findByUserId(Long userId);
}
