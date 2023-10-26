package com.esprit.Bizmatch.Forum.Repo;

import com.esprit.Bizmatch.Forum.Entity.LikeP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface LikePRepository extends JpaRepository<LikeP, Integer> {
Integer countByPostBlogIdpostBlog(Integer idpostBlog);
@Query("select count(*) from LikeP  ")
 Integer countAllLIkes();
}