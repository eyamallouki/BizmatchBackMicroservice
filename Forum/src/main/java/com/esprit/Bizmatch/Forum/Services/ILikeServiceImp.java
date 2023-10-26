package com.esprit.Bizmatch.Forum.Services;

import com.esprit.Bizmatch.Forum.Entity.LikeP;
import com.esprit.Bizmatch.Forum.Repo.LikePRepository;
import com.esprit.Bizmatch.Forum.Repo.PostBlogRepository;
import com.esprit.Bizmatch.Forum.Repo.UserDao;
import com.esprit.Bizmatch.Forum.generic.IGenericServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ILikeServiceImp  extends IGenericServiceImp<LikeP,Integer> implements ILikeService{
    private final PostBlogRepository postBlogRepository;
    private final UserDao userRepository;
    private final LikePRepository likePRepository;

    @Override
    public Integer countByPostBlogIdpostBlog(Integer idpostBlog) {
        return likePRepository.countByPostBlogIdpostBlog(idpostBlog);
    }

    @Override
    public Integer countTotalLikes() {
        return this.likePRepository.countAllLIkes();
    }


}
