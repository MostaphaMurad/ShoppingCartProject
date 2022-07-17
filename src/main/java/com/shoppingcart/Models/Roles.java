package com.shoppingcart.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false,unique = true,length = 25)
    String name;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "role")
    private List<Customers> customer;
    public int getId() {
        return id;
    }

    public List<Customers> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customers> customer) {
        this.customer = customer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
