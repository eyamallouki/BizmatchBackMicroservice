package com.esprit.Bizmatch.User.BizmatchUserAuthentification.Repository;

import com.esprit.Bizmatch.User.BizmatchUserAuthentification.entity.UserMail;

public interface IUserEmailRepository {
    public void sendCodeByMail(UserMail mail);
}
