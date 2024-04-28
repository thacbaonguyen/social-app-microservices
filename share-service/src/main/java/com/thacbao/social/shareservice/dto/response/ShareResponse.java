package com.thacbao.social.shareservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class ShareResponse <T>{
    private Long id;

    private Long userId;

    private Long postId;

    private String content;

    private String privacy;

    private LocalDateTime createdAt;

    private T result;
}
