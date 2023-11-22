package com.jamsheed.RecipeManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,scope = Comments.class,property = "commentId")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(nullable = false)
    private String commentBody;

    private LocalDateTime commentCreationTimeStamp;


    @ManyToOne
    @JoinColumn(name = "fk_recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "fk_commenter_id")
    private User commenter;
}
