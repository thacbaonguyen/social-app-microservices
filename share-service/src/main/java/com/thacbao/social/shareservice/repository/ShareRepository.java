package com.thacbao.social.shareservice.repository;

import com.thacbao.social.shareservice.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {
    List<Share> findByUserId(Long userId);
    List<Share> findByPostId(Long postId);
}
