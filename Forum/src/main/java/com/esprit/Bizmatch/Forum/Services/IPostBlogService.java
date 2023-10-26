package com.esprit.Bizmatch.Forum.Services;



import com.esprit.Bizmatch.Forum.Entity.Category;
import com.esprit.Bizmatch.Forum.Entity.Dislike;
import com.esprit.Bizmatch.Forum.Entity.LikeP;
import com.esprit.Bizmatch.Forum.Entity.PostBlog;
import com.esprit.Bizmatch.Forum.generic.IGenericService;

import java.util.List;

public interface IPostBlogService extends IGenericService<PostBlog,Integer> {
    void addAndAssignPostBlogToUser (PostBlog p, Integer id_User);
    List<PostBlog> findByCategoryOrderByPublishedDateDesc(Category category);
    public Category most_categoryUsed();
    List<PostBlog> findByUserUserNameOrderByPublishedDateDesc(String username);
    int nbrPostByUser(Integer id_User);
    LikeP addAndAssignLikeToPostAndUser(LikeP l, Integer idpostBlog);
    Dislike addAndAssignDislikeToPostAndUser(Dislike d, Integer idpostBlog);
     void deletePostForUser(Integer id_User, Integer idpostBlog);


}
