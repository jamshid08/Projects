package com.jamsheed.RecipeManagementSystem.controller;

import com.jamsheed.RecipeManagementSystem.model.Recipe;
import com.jamsheed.RecipeManagementSystem.model.User;
import com.jamsheed.RecipeManagementSystem.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class RecipeController {

    @Autowired
    RecipeService recipeService;

// todo: post Recipe
    @PostMapping("Recipe")
    public String createRecipe(@RequestParam @Valid String email, @RequestParam  @Valid String tokenValue, @RequestBody @Valid Recipe recipe)
    {
        return recipeService.createRecipe(email,tokenValue,recipe);
    }


    // todo : delete Recipe

    @DeleteMapping("recipe/{recipeId}")
    public String deleteRecipe(@RequestParam @Valid String email, @RequestParam @Valid String tokenValue, @PathVariable @Valid Integer recipeId)
    {
        return recipeService.deleteRecipe(email,tokenValue,recipeId);
    }


    // todo : update Recipe details
    @PutMapping("recipe/{recipeId}")
    public String updateRecipe(@RequestParam @Valid String email, @RequestParam @Valid String tokenValue, @PathVariable @Valid Integer recipeId, @RequestBody @Valid Recipe updateRecipe)
    {
        return recipeService.updateRecipe(email,tokenValue,recipeId,updateRecipe);
    }


    // todo : get all recipes
    @GetMapping("recipes/{email}/{tokenValue}")
    public List<Recipe> getAllRecipes(@PathVariable  @Valid String email, @PathVariable  @Valid String tokenValue){
        return recipeService.getAllRecipes(email,tokenValue);

    }


    // todo : get recipe by id

    @GetMapping("Recipe/{email}/{tokenValue}/{recipeId}")
    public Object getRecipeById(@PathVariable  @Valid String email, @PathVariable  @Valid String tokenValue,@PathVariable  @Valid Integer recipeId){
        return recipeService.getRecipeById(email,tokenValue,recipeId);

    }
}
