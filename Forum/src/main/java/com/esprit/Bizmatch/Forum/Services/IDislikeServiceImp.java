package com.esprit.Bizmatch.Forum.Services;

import com.esprit.Bizmatch.Forum.Entity.Dislike;
import com.esprit.Bizmatch.Forum.Repo.DislikeRepository;
import com.esprit.Bizmatch.Forum.generic.IGenericServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class IDislikeServiceImp extends IGenericServiceImp<Dislike,Integer> implements IDislikeService{
    private final DislikeRepository dislikeRepository;
    @Override
    public Integer countByPostBlogIdpostBlog(Integer idpostBlog) {
       return dislikeRepository.countByPostBlogIdpostBlog(idpostBlog);
    }

    @Override
    public Integer countTotaDisLikes() {
        return dislikeRepository.countAllDislIkes();
    }
}
