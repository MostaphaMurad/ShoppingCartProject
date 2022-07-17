package com.shoppingcart.Models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,unique = true,length = 45)
    private String email;
    @Column(nullable = false,unique = false,length = 100)
    private String password;
    @Column(nullable = false,unique = false,length = 45,name = "first_name")
    private String fname;
    @Column(nullable = false,unique = false,length = 45,name = "last_name")
    private String lname;
    @Column(nullable = false,unique = false,length = 100)
    private String address;
    @Column(length = 15,nullable = true)
    private String auth_provider;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Roles role;

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAuth_provider() {
        return auth_provider;
    }

    public void setAuth_provider(String auth_provider) {
        this.auth_provider = auth_provider;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", address='" + address + '\'' +
                ", auth_provider='" + auth_provider + '\'' +
                '}';
    }


}
