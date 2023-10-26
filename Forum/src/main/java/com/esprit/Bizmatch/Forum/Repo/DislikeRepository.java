package com.esprit.Bizmatch.Forum.Repo;

import com.esprit.Bizmatch.Forum.Entity.Dislike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DislikeRepository extends JpaRepository<Dislike, Integer> {
    Integer countByPostBlogIdpostBlog(Integer idpostBlog);
    @Query("select count(*) from Dislike  ")
    Integer countAllDislIkes();

}