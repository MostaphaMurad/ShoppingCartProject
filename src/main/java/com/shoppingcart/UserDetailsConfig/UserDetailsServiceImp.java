package com.shoppingcart.UserDetailsConfig;

import com.shoppingcart.Repositories.CustomerRepository;
import com.shoppingcart.Models.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private CustomerRepository cusRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customers cust=cusRepo.findByEmail(email);
        if(cust==null)throw new UsernameNotFoundException("Invalid Username ");
        else return new MyUserDetails(cust);
    }
}
