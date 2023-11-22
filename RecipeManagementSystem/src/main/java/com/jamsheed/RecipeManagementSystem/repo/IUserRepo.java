package com.jamsheed.RecipeManagementSystem.repo;

import com.jamsheed.RecipeManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Integer> {
    User findFirstByUserEmail(String newEmail);
}
