package com.esprit.Bizmatch.Forum.Repo;

import com.esprit.Bizmatch.Forum.Entity.Comment;
import com.esprit.Bizmatch.Forum.Entity.PostBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("select c.postBlog from Comment c ")
    List<PostBlog> getPosts();
    Integer countByPostBlog(PostBlog postBlog);

    @Query("SELECT c FROM Comment c LEFT JOIN c.underComments uc GROUP BY c.idcomment ORDER BY COUNT(uc) DESC")
    List<Comment> findCommentsOrderByUnderCommentCountDesc();

    @Query("select count(*) from Comment  ")
    Integer countAllLCommnts();



}