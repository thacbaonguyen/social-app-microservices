package com.thacbao.social.shareservice.dto.response;

import com.thacbao.social.shareservice.entity.Hashtag;
import com.thacbao.social.shareservice.entity.PostImage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponse {
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

    List<PostImage> postImageList;

    List<Hashtag> hashtag;
    Long like;
}
