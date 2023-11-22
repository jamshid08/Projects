package com.jamsheed.RecipeManagementSystem.service;

import com.jamsheed.RecipeManagementSystem.model.Comments;
import com.jamsheed.RecipeManagementSystem.model.Recipe;
import com.jamsheed.RecipeManagementSystem.model.User;
import com.jamsheed.RecipeManagementSystem.repo.ICommentsRepo;
import com.jamsheed.RecipeManagementSystem.repo.IRecipeRepo;
import com.jamsheed.RecipeManagementSystem.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    ICommentsRepo commentsRepo;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    IRecipeRepo recipeRepo;
    @Autowired
    IUserRepo userRepo;

    public void clearCommentsByPost(Recipe myRecipe) {
        List<Comments> commentsOfRecipe = commentsRepo.findByRecipe(myRecipe);
        commentsRepo.deleteAll(commentsOfRecipe);
    }

    // todo : code to add a comment

    public String addComment(String email, String tokenValue, String commentBody, Integer recipeId) {

        if(authenticationService.authenticate(email,tokenValue)) {

            //figure out the Recipe which we are commenting
            Recipe recipeToBeCommented = recipeRepo.findById(recipeId).orElseThrow();

            //we have to figure out the commentor
            User commentor = userRepo.findFirstByUserEmail(email);


            Comments newComment = new Comments(null,commentBody,
                    LocalDateTime.now(), recipeToBeCommented, commentor);

            commentsRepo.save(newComment);

            return commentor.getFirstName()+" "+commentor.getLastName() + " commented on " + recipeId;


        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public String removeComment(String email, String tokenValue, Integer commentId) {

        if(authenticationService.authenticate(email,tokenValue)) {
            Comments comment = commentsRepo.findById(commentId).orElseThrow();

            Recipe recipeOfComment = comment.getRecipe();


            if(authorizeCommentRemover(email,recipeOfComment,comment))
            {
                commentsRepo.deleteById(commentId);
                return "comment deleted";
            }
            else {
                return "Not authorized!!";
            }

        }
        else {
            return "Un Authenticated access!!!";
        }

    }

    private boolean authorizeCommentRemover(String email, Recipe recipeOfComment, Comments comment) {
        User potentialRemover = userRepo.findFirstByUserEmail(email);

        //only the commenter and the owner of the recipe should be allowed to remove a comment

        return potentialRemover.equals(recipeOfComment.getRecipeOwner()) || potentialRemover.equals(comment.getCommenter());
    }



    // todo : code to update a comment
    public String updateComment(String email, String tokenValue, Integer commentId, String commentBody) {
        if(authenticationService.authenticate(email,tokenValue)) {
            Comments comment = commentsRepo.findById(commentId).orElseThrow();

          //  Recipe recipeOfComment = comment.getRecipe();


            if(authorizeCommentUpdater(email,comment))
            {
                comment.setCommentBody(commentBody);
                commentsRepo.save(comment);
                return "comment updated";
            }
            else {
                return "Not authorized!!";
            }

        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    private boolean authorizeCommentUpdater(String email, Comments comment) {
        User potentialUpdater = userRepo.findFirstByUserEmail(email);


        //only the commenter of the recipe should be allowed to update a comment

        return potentialUpdater.equals(comment.getCommenter());
    }
}
