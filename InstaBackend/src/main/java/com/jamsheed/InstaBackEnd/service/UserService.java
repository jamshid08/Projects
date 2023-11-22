package com.jamsheed.InstaBackEnd.service;



import com.jamsheed.InstaBackEnd.model.AuthenticationToken;
import com.jamsheed.InstaBackEnd.model.Post;
import com.jamsheed.InstaBackEnd.model.User;

import com.jamsheed.InstaBackEnd.repository.IUserRepo;

import com.jamsheed.InstaBackEnd.service.emailUtility.EmailHandler;
import com.jamsheed.InstaBackEnd.service.hashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    PostService postService;


    public String userSignUp(User newUser) {

        String newEmail = newUser.getUserEmail();

        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null)
        {
            return "email already in use";
        }



        String signUpPassword = newUser.getUserPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);

            newUser.setUserPassword(encryptedPassword);


            userRepo.save(newUser);
            return "Instagram user registered";

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }

    }


    public String userSignIn(String email, String password) {

        User existingUser = userRepo.findFirstByUserEmail(email);

        if(existingUser == null)
        {
            return "Not a valid email, Please sign up first !!!";
        }



        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if(existingUser.getUserPassword().equals(encryptedPassword))
            {

                AuthenticationToken token  = new AuthenticationToken(existingUser);

                if(EmailHandler.sendEmail(email,"otp after login", token.getTokenValue())) {
                    authenticationService.createToken(token);
                    return "check email for otp/token!!!";
                }
                else {
                    return "error while generating token!!!";
                }

            }
            else {

                return "Invalid Credentials!!!";
            }

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }
    }

    public String userSignOut(String email,String tokenValue) {
        if(authenticationService.authenticate(email,tokenValue)) {
            authenticationService.deleteToken(tokenValue);
            return "Sign Out successful!!";
        }
        else {
            return "Un Authenticated access!!!";
        }
    }

    public String updateUser(String email, String tokenValue, User updateUser) {
        if(authenticationService.authenticate(email,tokenValue)){
            User existingUser = userRepo.findFirstByUserEmail(email);
            if(updateUser.getUserName() != null) {
                existingUser.setUserName(updateUser.getUserName());
            }
            if (updateUser.getUserHandle() != null) {
                existingUser.setUserHandle(updateUser.getUserHandle());
            }
            if (updateUser.getUserBio() != null) {
                existingUser.setUserBio(updateUser.getUserBio());
            }
            if (updateUser.getUserEmail() != null) {
                existingUser.setUserEmail(updateUser.getUserEmail());
            }
            if (updateUser.getUserPassword() != null) {
                try {
                    String updatePassword = updateUser.getUserPassword();
                    String encryptedPassword = PasswordEncryptor.encrypt(updatePassword);

                    updateUser.setUserPassword(encryptedPassword);


                } catch (NoSuchAlgorithmException e) {

                    return "Internal Server issues while saving password, try again later!!!";
                }
                existingUser.setUserPassword(updateUser.getUserPassword());
            }
            if (updateUser.getGender() != null) {
                existingUser.setGender(updateUser.getGender());
            }
            if (updateUser.getAccountType() != null) {
                existingUser.setAccountType(updateUser.getAccountType());
            }
            if (updateUser.isBlueTick() != existingUser.isBlueTick()) {
                existingUser.setBlueTick(updateUser.isBlueTick());
            }

            userRepo.save(existingUser);
            return "User details updated";

        }
        else {
            return "Un Authenticated access!!!";
        }
    }


}
