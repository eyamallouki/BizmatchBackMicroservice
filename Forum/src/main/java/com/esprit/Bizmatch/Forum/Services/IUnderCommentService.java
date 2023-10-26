package com.esprit.Bizmatch.Forum.Services;

import com.esprit.Bizmatch.Forum.Entity.UnderComment;
import com.esprit.Bizmatch.Forum.generic.IGenericService;

public interface IUnderCommentService extends IGenericService<UnderComment,Integer> {
  //  void assignUnderCommentToComment (Integer id_Comment, Integer id_UnderComment);

    UnderComment addUnderComment(UnderComment var1, Integer var2, Integer var3, Integer var4);


}
