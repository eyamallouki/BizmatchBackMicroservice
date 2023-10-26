package com.Bizmatch.PrestationServiceMS.Repository;

import com.Bizmatch.PrestationServiceMS.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, String> {
    @Query("SELECT r FROM Role r WHERE r.roleName = :name")
    Role findRoleByRoleName(@Param("name") String name);

}