package com.jamsheed.InstaBackEnd.controller;


import com.jamsheed.InstaBackEnd.model.Post;
import com.jamsheed.InstaBackEnd.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("InstaPost")
    public String createInstaPost(@RequestParam @Valid String email, @RequestParam @Valid String tokenValue, @RequestBody @Valid Post instaPost)
    {
        return postService.createInstaPost(email,tokenValue,instaPost);
    }


    @GetMapping("post/id/{postId}/")
    public Post getPostById(@PathVariable @Valid Integer postId ){
        return postService.getPostById(postId);
    }
}
