package com.esprit.Bizmatch.Forum.Services;

import com.esprit.Bizmatch.Forum.Entity.LikeP;
import com.esprit.Bizmatch.Forum.generic.IGenericService;


public interface ILikeService extends IGenericService<LikeP,Integer> {
    Integer countByPostBlogIdpostBlog(Integer idpostBlog);
    Integer countTotalLikes();

}
