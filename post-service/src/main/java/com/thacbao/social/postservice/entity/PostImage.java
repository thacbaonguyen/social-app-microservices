package com.thacbao.social.postservice.entity;

import lombok.Data;

@Data
public class PostImage {
    Long id;

    String url;

    Long postId;
}
