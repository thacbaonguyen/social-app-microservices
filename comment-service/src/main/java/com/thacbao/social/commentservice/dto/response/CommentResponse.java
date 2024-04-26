package com.thacbao.social.commentservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponse {
    private Long id;

    private String content;

    private Long userId;

    private Long postId;

    private Long commentReply;

    private Long likeCount;

    private LocalDateTime createdAt;
}
