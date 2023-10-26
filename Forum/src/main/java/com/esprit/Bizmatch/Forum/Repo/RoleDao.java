package com.esprit.Bizmatch.Forum.Repo;

import com.esprit.Bizmatch.Forum.Entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleDao extends CrudRepository<Role, String> {

}
