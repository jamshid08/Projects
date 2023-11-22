package com.jamsheed.InstaBackEnd.repository;

import com.jamsheed.InstaBackEnd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Integer> {

    User findFirstByUserEmail(String newEmail);
}
