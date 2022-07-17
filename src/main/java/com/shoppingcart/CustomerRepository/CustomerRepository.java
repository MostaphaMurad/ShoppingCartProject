package com.shoppingcart.CustomerRepository;

import com.shoppingcart.Models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers,Integer> {
    Customers findByEmail(String email);
}
