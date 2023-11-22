package com.jamsheed.InstaBackEnd.repository;

import com.jamsheed.InstaBackEnd.model.AuthenticationToken;
import com.jamsheed.InstaBackEnd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {


    AuthenticationToken findFirstByTokenValue(String authTokenValue);

    AuthenticationToken findFirstByUser(User user);
}
