package com.thacbao.social.likeservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "like_posts")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LikePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "post_id")
    Long postId;
}
