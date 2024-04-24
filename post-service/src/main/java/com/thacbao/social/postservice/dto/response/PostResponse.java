package com.thacbao.social.postservice.dto.response;

import com.thacbao.social.postservice.entity.Hashtag;
import com.thacbao.social.postservice.entity.PostImage;
import jakarta.persistence.Column;
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
