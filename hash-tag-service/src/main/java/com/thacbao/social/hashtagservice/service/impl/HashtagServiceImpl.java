package com.thacbao.social.hashtagservice.service.impl;

import com.thacbao.social.hashtagservice.dto.request.HashtagRequest;
import com.thacbao.social.hashtagservice.entity.Hashtag;
import com.thacbao.social.hashtagservice.repository.HashtagRepository;
import com.thacbao.social.hashtagservice.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {
    private final HashtagRepository hashtagRepository;
    @Override
    public String insertHashtag(Long postId, HashtagRequest request) {
        request.getHashtag().stream()
                .map(item -> {
                    Hashtag hashtag = new Hashtag();
                    hashtag.setHashtag(item);
                    hashtag.setPostId(postId);
                    return hashtag;
                })
                .forEach(hashtagRepository::save);
        return "Thêm thành công";
    }

    @Override
    public List<Hashtag> getHashtag(Long postId) {
        return hashtagRepository.findByPostId(postId);
    }

    @Override
    public List<Long> getPostId(String hashtag) {
        List<Hashtag> hashtags = hashtagRepository.findByHashtag(hashtag);
        return hashtags.stream()
                .map(Hashtag::getPostId)
                .collect(Collectors.toList());
    }
}
