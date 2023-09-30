package com.esprit.Bizmatch.User.BizmatchUserAuthentification.Repository;

import com.esprit.Bizmatch.User.BizmatchUserAuthentification.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleDao extends CrudRepository<Role, String> {

}
