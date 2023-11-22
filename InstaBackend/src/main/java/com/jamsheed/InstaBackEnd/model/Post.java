package com.jamsheed.InstaBackEnd.model;

import com.jamsheed.InstaBackEnd.model.enums.PostType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private String postData;
    private String postCaption;
    private String postLocation;
    @Enumerated(EnumType.STRING)
    private PostType postType;

    private LocalDateTime postCreatedTimeStamp;

    @ManyToOne
    @JoinColumn(name = "fk_owner_user_id")
    private User postOwner;
}
