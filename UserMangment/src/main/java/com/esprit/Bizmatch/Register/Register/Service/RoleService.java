package com.esprit.Bizmatch.Register.Register.Service;

import com.esprit.Bizmatch.Register.Register.Repository.RoleDao;
import com.esprit.Bizmatch.Register.Register.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}
