package com.thacbao.social.likeservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "like_comments")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LikeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "comment_id")
    Long commentId;
}
