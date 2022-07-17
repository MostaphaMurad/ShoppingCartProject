package com.shoppingcart.UserDetailsConfig;

import com.shoppingcart.Models.Customers;
import com.shoppingcart.Models.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;

public class MyUserDetails  implements UserDetails {
    private Customers customer;
    public Integer getId(){
        return this.customer.getId();
    }
    public String getFirstName(){
        return this.customer.getFname();
    }
    public String getEmail(){
        return this.customer.getEmail();
    }
    public MyUserDetails(Customers cust) {
        this.customer=cust;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Roles roles= customer.getRole();
        List<SimpleGrantedAuthority>authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(roles.getName()));
        System.out.println("grand auth "+roles.getName());
        System.out.println(authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
