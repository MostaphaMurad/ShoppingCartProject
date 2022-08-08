package com.shoppingcart.Services;

import com.shoppingcart.Models.Roles;
import com.shoppingcart.Repositories.RoleRepository;
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
