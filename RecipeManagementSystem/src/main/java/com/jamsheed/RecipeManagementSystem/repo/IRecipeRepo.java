package com.jamsheed.RecipeManagementSystem.repo;

import com.jamsheed.RecipeManagementSystem.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecipeRepo extends JpaRepository<Recipe,Integer> {
}
