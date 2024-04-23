package com.thacbao.social.hashtagservice.service;

import com.thacbao.social.hashtagservice.dto.request.HashtagRequest;
import com.thacbao.social.hashtagservice.entity.Hashtag;

import java.util.List;

public interface HashtagService {
    String insertHashtag(Long postId, HashtagRequest request);

    List<Hashtag> getHashtag(Long postId);
    List<Long> getPostId(String hashtag);
}
