package com.esprit.Bizmatch.Forum.Repo;

import com.esprit.Bizmatch.Forum.Entity.ImagePost;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ImagePostRepository extends JpaRepository<ImagePost, Integer> {
    Optional<ImagePost> findByName (String name);
    Optional<ImagePost>findByPostBlog_IdpostBlog(Integer idpostBlog);

}