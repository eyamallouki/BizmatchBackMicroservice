package com.esprit.Bizmatch.User.BizmatchUserAuthentification.Service;
import com.esprit.Bizmatch.User.BizmatchUserAuthentification.Repository.RoleDao;
import com.esprit.Bizmatch.User.BizmatchUserAuthentification.entity.Role;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}
