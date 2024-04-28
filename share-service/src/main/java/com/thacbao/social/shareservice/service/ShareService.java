package com.thacbao.social.shareservice.service;

import com.thacbao.social.shareservice.dto.request.ShareRequest;
import com.thacbao.social.shareservice.dto.response.ShareResponse;

import java.util.List;

public interface ShareService {
    String sharePost(Long userId, Long postId, ShareRequest request);

    List<ShareResponse> getShareByUserId(Long userId);

    List<ShareResponse> getShareByPostId(Long postId);

    String updateSharePost(Long userId, Long shareId, ShareRequest request);

    String deleteSharePost(Long userId, Long id);
}
