package com.thacbao.social.shareservice.entity;

import lombok.Data;

@Data
public class PostImage {
    Long id;

    String url;

    Long postId;
}