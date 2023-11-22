package com.jamsheed.RecipeManagementSystem.repo;

import com.jamsheed.RecipeManagementSystem.model.Comments;
import com.jamsheed.RecipeManagementSystem.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentsRepo  extends JpaRepository<Comments,Integer> {
    List<Comments> findByRecipe(Recipe myRecipe);
}
