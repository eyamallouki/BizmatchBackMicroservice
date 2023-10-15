package com.esprit.Bizmatch.Register.Register.Repository;

import com.esprit.Bizmatch.Register.Register.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleDao extends CrudRepository<Role, String> {
}
