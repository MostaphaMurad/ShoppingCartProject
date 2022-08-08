package com.shoppingcart.Services;

import com.shoppingcart.Models.Products;
import com.shoppingcart.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {
    @Autowired
    ProductRepository ProdRep;
    public Products saveProduct(Products product) {
        Products p=ProdRep.save(product);
        if(p.getId()>0)return p;
        else return null;
    }

    public List<Products> getAllProducts() {
        return ProdRep.findAll();
    }

    public Products getProductById(int id) {
        Products p=ProdRep.findById(id).get();
        if(p==null)return null;
        else return p;
    }

    public boolean delProduct(int id) {
        int cnt=ProdRep.countById(id);
        if(cnt>0) {ProdRep.deleteById(id);return true;}
        else return false;
    }
}
