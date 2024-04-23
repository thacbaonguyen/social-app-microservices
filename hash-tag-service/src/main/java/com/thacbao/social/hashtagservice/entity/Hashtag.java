package com.thacbao.social.hashtagservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Entity
@Table(name = "hashtags")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String hashtag;

    Long postId;

}
