package com.jamsheed.InstaBackEnd.controller;





import com.jamsheed.InstaBackEnd.model.User;
import com.jamsheed.InstaBackEnd.service.AuthenticationService;
import com.jamsheed.InstaBackEnd.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;


    @PostMapping("user/signup")
    public String userSignUp(@Valid @RequestBody User newUser)
    {

        return userService.userSignUp(newUser);
    }




    @PostMapping("user/signIn/{email}/{password}")
    public String userSignIn(@PathVariable @Valid String email,@PathVariable  @Valid String password)
    {
        return userService.userSignIn(email,password);
    }



    @DeleteMapping("user/signOut")
    public String userSignOut(@RequestParam @Valid String email, @RequestParam @Valid String tokenValue)
    {
        return userService.userSignOut(email,tokenValue);
    }



    @PutMapping("user/changes")

    public String updateUser(@RequestParam  @Valid String email, @RequestParam  @Valid String tokenValue,@RequestBody @Valid User updateUser)
    {
        return userService.updateUser(email,tokenValue,updateUser);
    }
}


