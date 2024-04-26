package com.thacbao.social.hashtagservice.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HashtagRequest {
    List<String> hashtag;
}
