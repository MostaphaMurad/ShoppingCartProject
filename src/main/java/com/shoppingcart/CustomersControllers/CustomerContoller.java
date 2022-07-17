package com.shoppingcart.CustomersControllers;

import com.shoppingcart.CartItemRepository.CartItemRepo;
import com.shoppingcart.CartItemServices.CartItemServices;
import com.shoppingcart.CustomerServices.CustomerServices;
import com.shoppingcart.Home.homeController;
import com.shoppingcart.Models.CartItem;
import com.shoppingcart.Models.Customers;
import com.shoppingcart.Models.Products;
import com.shoppingcart.ProductsServices.ProductServices;
import com.shoppingcart.ShoppingCartServices.ShoppingCartServices;
import com.shoppingcart.UserDetailsConfig.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/cust","/customer","/customers"})
public class CustomerContoller {
    @Autowired
    CartItemServices cartServ;
    @Autowired
    private CustomerServices CusServ;
    @Autowired
    private ProductServices ProdServ;
    @Autowired
    private ShoppingCartServices shopServ;
    @RequestMapping("/addcart/{id}")
    public String CartItemToCustomerId(@PathVariable("id")int id, Model model, RedirectAttributes rd){
         Products product=ProdServ.getProductById(id);
        model.addAttribute("product",product);
        //System.out.println("product in get method= "+product);
        model.addAttribute("cart",new CartItem());
        return "cartform";
    }
    @RequestMapping(value = "/addcart/{id}",method = RequestMethod.POST)
    public String addCart(@PathVariable("id")int id,@RequestParam("quantity")int quan, @AuthenticationPrincipal MyUserDetails userDetails, Model model){
        String loggedEmail=userDetails.getEmail();
        int loggedId=userDetails.getId();
        Customers c=CusServ.getCustomerById(loggedId);
        CartItem cartItem=new CartItem();
        Products product=ProdServ.getProductById(id);
        if(quan>product.getIn_stock()){
            return "errorpage";
        }
        else{
            cartItem.setQuantity(quan);
            cartItem.setProduct(product);
            cartItem.setCustomer(c);
            Products newProd=product;
            int difQuan=product.getIn_stock()-quan;
            newProd.setIn_stock(difQuan);
            ProdServ.saveProduct(newProd);
            cartServ.SaveCartItemToCustomer(cartItem);
            return  "redirect:/prod/allproduct";
        }
    }
}
