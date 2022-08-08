package com.shoppingcart.Services;

import com.shoppingcart.Repositories.CustomerRepository;
import com.shoppingcart.Models.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServices {
    @Autowired
    CustomerRepository CusRepo;

    public boolean getCustomerByEmail(String email,String password) {
        Customers customer=CusRepo.findByEmail(email);
        if(customer.getPassword().equals(password))return true;
        else return false;
    }
    public Customers getCustomerById(int id){
        return CusRepo.findById(id).get();
    }
    public boolean CreateCustomer(Customers customer) {
        Customers cus=CusRepo.save(customer);
        System.out.println("from customer service create customer method  "+ customer);

        if(cus.getId()>0)return true;
        else return false;
    }
}
