package com.thacbao.social.postservice.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hashtag {
    Long id;

    String hashtag;

    Long postId;
}
