package com.esprit.Bizmatch.Forum.Repo;

import com.esprit.Bizmatch.Forum.Entity.Category;
import com.esprit.Bizmatch.Forum.Entity.PostBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface PostBlogRepository extends JpaRepository<PostBlog, Integer> {

    //Integer countByUserid_User(Integer id_User);

    @Query("SELECT COUNT(p) FROM PostBlog p WHERE p.user.userName = :id_User")
    Integer countPostBlogByUseridUser(@Param("id_User") Integer id_User);
    List<PostBlog> findByCategoryOrderByPublishedDateDesc(Category category);
    List<PostBlog> findByUserUserNameOrderByPublishedDateDesc(String username);

    @Query("SELECT MAX(p.idpostBlog) FROM PostBlog p")
    Long findLastInsertedId();



    }