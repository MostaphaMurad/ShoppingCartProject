package com.shoppingcart.Services;

import com.shoppingcart.Repositories.CartItemRepo;
import com.shoppingcart.Models.CartItem;
import com.shoppingcart.Models.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServices {
    @Autowired
    CartItemRepo itemRepo;
    public List<CartItem>ListCartItems(Customers customer){
        return itemRepo.findByCustomer(customer);
    }
    public void save(CartItem cartItem){
        itemRepo.save(cartItem);
    }
}
