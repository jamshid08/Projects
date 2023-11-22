package com.jamsheed.RecipeManagementSystem.controller;

import com.jamsheed.RecipeManagementSystem.model.User;
import com.jamsheed.RecipeManagementSystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;

// todo: signUp
    @PostMapping("user/signup")
    public String userSignUp(@Valid @RequestBody User newUser)
    {

        return userService.userSignUp(newUser);
    }


    // todo: signIn

    @PostMapping("user/signIn/{email}/{password}")
    public String userSignIn(@PathVariable  @Valid String email, @PathVariable  @Valid String password)
    {
        return userService.userSignIn(email,password);
    }



    // todo: signOut
    @DeleteMapping("user/signOut")
    public String userSignOut(@RequestParam  @Valid String email, @RequestParam String tokenValue)
    {
        return userService.userSignOut(email,tokenValue);
    }




    // todo: update UserDetails


    @PutMapping("user/changes/{email}/{tokenValue}/{userId}")

    public String updateUser(@PathVariable  @Valid String email, @PathVariable  @Valid String tokenValue,@PathVariable  @Valid Integer userId,@RequestBody @Valid User updateUser)
    {
        return userService.updateUser(email,tokenValue,updateUser);
    }



    // todo: get all users
    @GetMapping("users/{email}/{tokenValue}")
    public List<User> getAllUsers(@PathVariable  @Valid String email, @PathVariable  @Valid String tokenValue){
        return userService.getAllUsers(email,tokenValue);

    }


    // todo: get user by id
    @GetMapping("user/{email}/{tokenValue}/{userId}")
    public Object getUserById(@PathVariable  @Valid String email, @PathVariable  @Valid String tokenValue,@PathVariable  @Valid Integer userId){
        return userService.getUserById(email,tokenValue,userId);

    }

}
