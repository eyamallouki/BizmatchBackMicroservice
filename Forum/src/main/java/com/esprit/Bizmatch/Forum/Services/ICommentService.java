package com.esprit.Bizmatch.Forum.Services;



import com.esprit.Bizmatch.Forum.Entity.Comment;
import com.esprit.Bizmatch.Forum.Entity.PostBlog;
import com.esprit.Bizmatch.Forum.generic.IGenericService;

import java.util.List;

public interface ICommentService extends IGenericService<Comment,Integer> {
    Comment addComment(Integer var2, Integer var3,Comment var1);
    public Comment addComment2( Integer idpostBlog,Comment c);
    PostBlog mostCommentedPost();
    int getNbComment(Integer idpostBlog);
    List<Comment> commentairesPertinents ();
    List<Comment> getCommentsForPost(Integer idpostBlog);
    void deleteComment(Integer idcomment,Integer idpostBlog);

    Integer countTotaComments();

}
