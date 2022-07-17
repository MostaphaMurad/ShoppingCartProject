package com.shoppingcart.RoleServices;

import com.shoppingcart.Models.Roles;
import com.shoppingcart.RolesRepo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServices {
    @Autowired
    RoleRepository roleRepo;

    public List<Roles> getAllRoles() {
        return roleRepo.findAll();
    }
}
