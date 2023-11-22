package com.jamsheed.RecipeManagementSystem.service;

import com.jamsheed.RecipeManagementSystem.model.Recipe;
import com.jamsheed.RecipeManagementSystem.model.User;
import com.jamsheed.RecipeManagementSystem.repo.IRecipeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class RecipeService {

    @Autowired
    IRecipeRepo recipeRepo;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;


    // todo : create recipe code
    public String createRecipe(String email, String tokenValue, Recipe recipe) {
        if(authenticationService.authenticate(email,tokenValue)) {

            User existingUser = userService.findFirstByUserEmail(email);
            recipe.setRecipeOwner(existingUser);
            recipe.setRecipeCreationTime(LocalDateTime.now());

//now save the recipe in table
            recipeRepo.save(recipe);
            return "Recipe Name: "+ recipe.getRecipeName() +"  of Type: "+recipe.getRecipeType() +" posted!!";

        }
        else {
            return "Un Authenticated access!!!";
        }
    }



    // todo : delete recipe code
    public String deleteRecipe(String email, String tokenValue, Integer recipeId) {
        if(authenticationService.authenticate(email,tokenValue)) {

            Recipe existingRecipe =  recipeRepo.findById(recipeId).orElseThrow();
            String  recipeOwnerEmail =  existingRecipe.getRecipeOwner().getUserEmail();

            if(email.equals(recipeOwnerEmail))
            {
                    //delete recipe
                    removeById(recipeId);
                    return "Recipe removed!!";

            }
            else {
                return "Un authorized access!!";
            }


        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    @Transactional
    public void removeById(Integer recipeId) {

        Recipe myRecipe = recipeRepo.findById(recipeId).orElseThrow();



        // delete all comments
        commentService.clearCommentsByPost(myRecipe);

        //finally delete
        recipeRepo.deleteById(recipeId);
    }



    // todo : update recipe code
    public String updateRecipe(String email, String tokenValue, Integer recipeId, Recipe updateRecipe) {
        if(authenticationService.authenticate(email,tokenValue)) {

            Recipe existingRecipe =  recipeRepo.findById(recipeId).orElseThrow();
            String  recipeOwnerEmail =  existingRecipe.getRecipeOwner().getUserEmail();

            if(email.equals(recipeOwnerEmail))
            {
                if(updateRecipe.getRecipeName() != null) {
                    existingRecipe.setRecipeName(updateRecipe.getRecipeName());
                }
                if (updateRecipe.getRecipeInstructions() != null) {
                    existingRecipe.setRecipeInstructions(updateRecipe.getRecipeInstructions());
                }

                if (updateRecipe.getRecipeIngredients() != null) {
                    existingRecipe.setRecipeIngredients(updateRecipe.getRecipeIngredients());
                }
                if (updateRecipe.getRecipeType() != null) {
                    existingRecipe.setRecipeType(updateRecipe.getRecipeType());
                }
                recipeRepo.save(existingRecipe);
                return "Recipe updated!!";

            }
            else {
                return "Un authorized access!!";
            }


        }
        else {
            return "Un Authenticated access!!!";
        }

    }


    // todo : get all recipes
    public List<Recipe> getAllRecipes(String email, String tokenValue) {
        if(authenticationService.authenticate(email,tokenValue)){

            return recipeRepo.findAll();

        }
        else {
            throw new RuntimeException("Un Authenticated access!!");
        }
    }

    public Object getRecipeById(String email, String tokenValue, Integer recipeId) {

        if(authenticationService.authenticate(email,tokenValue)){
            Recipe existingRecipe = recipeRepo.findById(recipeId).orElse(null);

            if(existingRecipe != null){
                return existingRecipe;
            }else {
                return "no such recipe of ID: " +recipeId;
            }


            }
        else {
            return "Un Authenticated access!!!";
        }
    }
}
