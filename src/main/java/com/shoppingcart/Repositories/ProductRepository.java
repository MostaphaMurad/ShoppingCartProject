package com.shoppingcart.Repositories;

import com.shoppingcart.Models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {
    int countById(int id);
}
