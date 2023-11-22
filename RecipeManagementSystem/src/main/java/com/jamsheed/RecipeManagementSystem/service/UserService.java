package com.jamsheed.RecipeManagementSystem.service;

import com.jamsheed.RecipeManagementSystem.model.AuthenticationToken;
import com.jamsheed.RecipeManagementSystem.model.User;
import com.jamsheed.RecipeManagementSystem.repo.IUserRepo;
import com.jamsheed.RecipeManagementSystem.service.emailUtility.EmailHandler;
import com.jamsheed.RecipeManagementSystem.service.hashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;
    @Autowired
    AuthenticationService authenticationService;


//todo: signUp service code
    public String userSignUp(User newUser) {
        //check if already exist -> Not allowed : try logging in

        String newEmail = newUser.getUserEmail();

        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null)
        {
            return "email already in use";
        }

        // passwords are encrypted before we store it in the table

        String signUpPassword = newUser.getUserPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);

            newUser.setUserPassword(encryptedPassword);



            userRepo.save(newUser);
            return "user registered";

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }

    }



    //todo: signIn service code

    public String userSignIn(String email, String password) {


        //check if the email is there in your tables
        //sign in only possible if this person ever signed up


        User existingUser = userRepo.findFirstByUserEmail(email);

        if(existingUser == null)
        {
            return "Not a valid email, Please sign up first !!!";
        }

        //password should be matched

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if(existingUser.getUserPassword().equals(encryptedPassword))
            {
                // return a token for this sign in
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
                //password was wrong!!!
                return "Invalid Credentials!!!";
            }

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }

    }




    //todo: signOut service code

    public String userSignOut(String email, String tokenValue) {

        if(authenticationService.authenticate(email,tokenValue)) {
            authenticationService.deleteToken(tokenValue);
            return "Sign Out successful!!";
        }
        else {
            return "Un Authenticated access!!!";
        }
    }



    // todo : update user details  code
    public String updateUser(String email, String tokenValue, User updateUser) {
        if(authenticationService.authenticate(email,tokenValue)){
            User existingUser = userRepo.findFirstByUserEmail(email);
            if(updateUser.getFirstName() != null) {
                existingUser.setFirstName(updateUser.getFirstName());
            }
            if (updateUser.getLastName() != null) {
                existingUser.setLastName(updateUser.getLastName());
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
            if (updateUser.getUserGender() != null) {
                existingUser.setUserGender(updateUser.getUserGender());
            }
            if (updateUser.getUserDOB() != null) {
                existingUser.setUserDOB(updateUser.getUserDOB());
            }
            if (updateUser.getUserPhoneNumber() != null) {
                existingUser.setUserPhoneNumber(updateUser.getUserPhoneNumber());
            }

            userRepo.save(existingUser);
            return "User details updated";

        }
        else {
            return "Un Authenticated access!!!";
        }
    }




    // todo : get user   code
    public List<User> getAllUsers(String email, String tokenValue) {

        if(authenticationService.authenticate(email,tokenValue)){

            return userRepo.findAll();

        }
        else {
            throw new RuntimeException("Un Authenticated access!!");
                          }
    }



    // todo : get user by id  code
    public Object getUserById(String email, String tokenValue, Integer userId) {
        if(authenticationService.authenticate(email,tokenValue)){
            User existingUser = userRepo.findById(userId).orElse(null);

            if(existingUser != null){
                return existingUser;
            }else {
                return "no such user of ID: " +userId;
            }
        }
        else {
            //Object o = "Un Authenticated access!!";
            return "Un Authenticated access!!!";
        }
    }

    public User findFirstByUserEmail(String email) {
      return   userRepo.findFirstByUserEmail(email);
    }
}




