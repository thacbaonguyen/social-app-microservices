package com.thacbao.social.postservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String content;

    String location;

    String privacy;

    @Column(name = "like_count")
    Long likeCount;

    @Column(name = "comment_count")
    Long commentCount;

    @Column(name = "share_count")
    Long shareCount;

    @Column(name = "save_count")
    Long saveCount;

    Long userId;
}
