package com.esprit.Bizmatch.User.BizmatchUserAuthentification.Controller;

import com.esprit.Bizmatch.User.BizmatchUserAuthentification.Service.RoleService;
import com.esprit.Bizmatch.User.BizmatchUserAuthentification.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }
}
