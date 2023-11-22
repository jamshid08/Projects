package com.jamsheed.RecipeManagementSystem.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.jamsheed.RecipeManagementSystem.model.enums.Gender;
import com.jamsheed.RecipeManagementSystem.model.enums.Type;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,scope = Recipe.class,property = "recipeId")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipeId;
    @Pattern(regexp = "[a-z A-Z]+")
    private String recipeName;
    private String recipeInstructions;
    private String recipeIngredients;

    @Enumerated(EnumType.STRING)
    private Type recipeType;

    private LocalDateTime recipeCreationTime;

    @ManyToOne
    @JoinColumn(name = "fk_owner_user_id")
    private User recipeOwner;

    @OneToMany(mappedBy ="recipe")
    List<Comments> commentsList;
}
