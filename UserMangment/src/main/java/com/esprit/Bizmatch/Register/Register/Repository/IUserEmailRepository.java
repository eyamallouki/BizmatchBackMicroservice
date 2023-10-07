package com.esprit.Bizmatch.Register.Register.Repository;

import com.esprit.Bizmatch.Register.Register.entity.UserMail;

public interface IUserEmailRepository {
    public void sendCodeByMail(UserMail mail);
}
