package com.thacbao.social.commentservice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post{
    Long id;

    String title;

    String content;

    String location;

    String privacy;

    Long likeCount;

    Long commentCount;

    Long shareCount;

    Long saveCount;

    Long userId;

    LocalDateTime createdAt;
}
