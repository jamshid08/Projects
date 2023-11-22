package com.jamsheed.InstaBackEnd.repository;

import com.jamsheed.InstaBackEnd.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends JpaRepository<Post,Integer> {
}
