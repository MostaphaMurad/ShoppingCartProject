package com.shoppingcart;

import com.shoppingcart.Repositories.CartItemRepo;
import com.shoppingcart.Models.CartItem;
import com.shoppingcart.Models.Customers;
import com.shoppingcart.Models.Products;
import com.shoppingcart.Models.Roles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ShoppingCartTests {
    @Autowired
    CartItemRepo cartRepo;
    @Autowired
    TestEntityManager testEntityManager;
    @Test
    public void test(){}
    @Test
    public void TestAssignRoleToCustomer(){
        Roles role=testEntityManager.find(Roles.class,2);
        Customers customer=testEntityManager.find(Customers.class,1);
        customer.setRole(role);
    }
    @Test
    public void TestAddOneCartItem(){
        Products products=testEntityManager.find(Products.class,1);
        Customers customer=testEntityManager.find(Customers.class,2);
        CartItem item=new CartItem();
        item.setCustomer(customer);
        item.setProduct(products);
        item.setQuantity(2);
        cartRepo.save(item);
    }
    @Test
    public void TestGetCartItemsByCustomer(){
        Customers cus=new Customers();
        cus.setId(1);
        List<CartItem>items=cartRepo.findByCustomer(cus);
        if (items.size()==1)System.out.println("True");
        else
            System.out.println("False");
    }
}
