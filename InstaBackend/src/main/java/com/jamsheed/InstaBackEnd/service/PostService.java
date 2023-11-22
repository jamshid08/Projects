package com.jamsheed.InstaBackEnd.service;


import com.jamsheed.InstaBackEnd.model.Post;
import com.jamsheed.InstaBackEnd.model.User;
import com.jamsheed.InstaBackEnd.repository.IPostRepo;
import com.jamsheed.InstaBackEnd.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {

    @Autowired
    IPostRepo postRepo;
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    IUserRepo userRepo;


    public String createInstaPost(String email, String tokenValue, Post instaPost) {
        if(authenticationService.authenticate(email,tokenValue)) {

            User existingUser = userRepo.findFirstByUserEmail(email);
            instaPost.setPostOwner(existingUser);
            instaPost.setPostCreatedTimeStamp(LocalDateTime.now());

            postRepo.save(instaPost);

            return instaPost.getPostType() + " posted!!";

        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public Post getPostById(Integer postId) {
      return  postRepo.findById(postId).orElseThrow();

    }

}
