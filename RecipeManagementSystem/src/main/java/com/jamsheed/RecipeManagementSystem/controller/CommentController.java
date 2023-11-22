package com.jamsheed.RecipeManagementSystem.controller;

import com.jamsheed.RecipeManagementSystem.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class CommentController {

    @Autowired
    CommentService commentService;


// todo : add a comment
    @PostMapping("comment/recipe/{recipeId}")
    public String addComment(@RequestParam @Valid String email, @RequestParam @Valid String tokenValue, @PathVariable @Valid Integer recipeId, @RequestBody @Valid String commentBody )
    {
        return commentService.addComment(email,tokenValue,commentBody,recipeId);
    }



    // todo : delete a comment

    @DeleteMapping("Recipe/comment/{commentId}")
    public String removeComment(@RequestParam @Valid String email, @RequestParam @Valid String tokenValue,
                                @PathVariable @Valid Integer commentId)
    {
        return commentService.removeComment(email,tokenValue,commentId);
    }

    // todo : update a comment
    @PutMapping("Recipe/comment/{commentId}")
    public String updateComment(@RequestParam @Valid String email, @RequestParam @Valid String tokenValue,
                                @PathVariable @Valid Integer commentId, @RequestBody @Valid String commentBody)
    {
        return commentService.updateComment(email,tokenValue,commentId,commentBody);
    }

}
