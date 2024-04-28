package com.thacbao.social.shareservice.service.impl;

import com.thacbao.social.shareservice.dto.request.ShareRequest;
import com.thacbao.social.shareservice.dto.response.PostResponse;
import com.thacbao.social.shareservice.dto.response.ShareResponse;
import com.thacbao.social.shareservice.entity.Share;
import com.thacbao.social.shareservice.exception.DataNotFoundException;
import com.thacbao.social.shareservice.exception.PermissionException;
import com.thacbao.social.shareservice.repository.ShareRepository;
import com.thacbao.social.shareservice.service.PostService;
import com.thacbao.social.shareservice.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShareServiceImpl implements ShareService {
    private final ShareRepository shareRepository;
    private final PostService postService;
    private final ModelMapper modelMapper;
    @Override
    public String sharePost(Long userId, Long postId, ShareRequest request) {
        boolean postExist = postService.existPost(postId);
        if (!postExist){
            throw new DataNotFoundException("Khong tim thay bai viet");
        }
        Share share = Share.builder()
                .content(request.getContent())
                .privacy(request.getPrivacy())
                .userId(userId)
                .postId(postId)
                .build();
        shareRepository.save(share);
        return "Share thanh cong";
    }

    @Override
    public List<ShareResponse> getShareByUserId(Long userId) {
        List<ShareResponse> shares = shareRepository.findByUserId(userId)
                .stream().map(share -> {
                    ShareResponse response = modelMapper.map(share, ShareResponse.class);
                    PostResponse post = postService.getPostById(share.getPostId());
                    response.setResult(post);
                    return response;
                }).toList();
        return shares;
    }

    @Override
    public List<ShareResponse> getShareByPostId(Long postId) {
        List<ShareResponse> responses = shareRepository.findByPostId(postId)
                .stream().map(share -> {
                    ShareResponse response = modelMapper.map(share, ShareResponse.class);
                    PostResponse post = postService.getPostById(share.getPostId());
                    response.setResult(post);
                    return response;
                }).toList();
        return responses;
    }

    @Override
    public String updateSharePost(Long userId, Long shareId, ShareRequest request) {
        Share share = shareRepository.findById(shareId).orElseThrow(() ->
                new DataNotFoundException("Khong tim thay luot chia se"));
        if (share.getUserId() != userId){
            throw new PermissionException("Khong co quyen chinh sua luot chia se nay");
        }
        share.setContent(request.getContent());
        share.setPrivacy(request.getPrivacy());
        shareRepository.save(share);
        return "update thanh cong";
    }

    @Override
    public String deleteSharePost(Long userId, Long id) {
        Share share = shareRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Khong tim thay luot chia se"));
        if (share.getUserId() != userId){
            throw new PermissionException("Khong co quyen xoa luot chia se nay");
        }
        shareRepository.delete(share);
        return "delete thanh cong";
    }
}
