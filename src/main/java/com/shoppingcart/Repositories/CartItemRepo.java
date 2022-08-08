package com.shoppingcart.Repositories;

import com.shoppingcart.Models.CartItem;
import com.shoppingcart.Models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem,Integer> {
    List<CartItem>findByCustomer(Customers customer);

    CartItem  findByProduct_id(int id);

    List<CartItem> findAllByProduct_id(int id);

    List<CartItem> findAllByCustomer_id(int loggedID);
}
