package com.thacbao.social.hashtagservice.repository;

import com.thacbao.social.hashtagservice.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    List<Hashtag> findByPostId(Long postId);
    List<Hashtag> findByHashtag(String hashtag);
}
