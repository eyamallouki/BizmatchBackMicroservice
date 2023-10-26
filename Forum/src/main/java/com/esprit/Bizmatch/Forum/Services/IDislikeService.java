package com.esprit.Bizmatch.Forum.Services;

import com.esprit.Bizmatch.Forum.Entity.Dislike;
import com.esprit.Bizmatch.Forum.generic.IGenericService;


public interface IDislikeService extends IGenericService<Dislike,Integer> {
    Integer countByPostBlogIdpostBlog(Integer idpostBlog);
    Integer countTotaDisLikes();

}
