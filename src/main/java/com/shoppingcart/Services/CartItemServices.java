package com.shoppingcart.Services;

import com.shoppingcart.Repositories.CartItemRepo;
import com.shoppingcart.Models.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServices {
    @Autowired
    CartItemRepo CartRepo;

    public void SaveCartItemToCustomer(CartItem cartItem) {
        CartRepo.save(cartItem);
    }

    public List<CartItem> findCartsWithProductId(int id) {
        List<CartItem> items=new ArrayList<>();
        items= CartRepo.findAllByProduct_id(id);
       System.out.println(items);
       return items;
    }

    public boolean delCartItemWithProdId(List<CartItem> item) {
        CartRepo.deleteAll(item);
        return true;
    }

    public List<CartItem> findCartsWithCustomerId(int loggedID) {
        List<CartItem>userCarts=CartRepo.findAllByCustomer_id(loggedID);
        return userCarts;
    }
}
