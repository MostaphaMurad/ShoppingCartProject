package com.shoppingcart.Home;

import com.shoppingcart.CartItemServices.CartItemServices;
import com.shoppingcart.CustomerServices.CustomerServices;
import com.shoppingcart.Models.CartItem;
import com.shoppingcart.Models.Customers;
import com.shoppingcart.Models.Products;
import com.shoppingcart.Models.Roles;
import com.shoppingcart.RoleServices.RoleServices;
import com.shoppingcart.UserDetailsConfig.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"/","/home"})
public class homeController {
    @Autowired
    CustomerServices custServ;
    @Autowired
    RoleServices roleServ;
    @Autowired private CartItemServices cartServ;
  /*  @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("customer",new Customers());
        return "login";
    }
    @RequestMapping(value = "/loginme" ,method = RequestMethod.POST)
    public String validatelogging(@ModelAttribute("customer")Customers customer, @RequestParam("password")String pass){
        System.out.println(customer.getEmail());
        boolean validuser=custServ.getCustomerByEmail(customer.getEmail(),pass);
        if(validuser){
            return "redirect:/prod/allproduct";
        }
        else return "errorpage";
    }*/
    @RequestMapping("/")
    public String home(@AuthenticationPrincipal MyUserDetails userdetails,Model model){
        if(userdetails==null)return "login";
        else{
            String fname=userdetails.getFirstName();
            model.addAttribute("fname",fname);
            return "index";
        }
    }
    @RequestMapping("/register")
    public String addCustomer(Model model){
        model.addAttribute("customer",new Customers());
        List<Roles>roles=roleServ.getAllRoles();
        model.addAttribute("roles",roles);
        return "register";
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String SaveCustomer(@ModelAttribute("customer")Customers customer,@ModelAttribute("roles")Roles roles){
        String pass=customer.getPassword();
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedPass=encoder.encode(pass);
        customer.setPassword(encodedPass);
        boolean savedCustomer=custServ.CreateCustomer(customer);
        if(savedCustomer)return "redirect:/login";
        else return "errorpage";
    }
    @RequestMapping(value = "/mycarts",method = RequestMethod.GET)
    public String ShowMyCarts(@AuthenticationPrincipal MyUserDetails userDetails,Model model){
        String LoggedEmail=userDetails.getEmail();
        int LoggedID=userDetails.getId();
        String fname=userDetails.getFirstName();
        List<CartItem>MyCarts=cartServ.findCartsWithCustomerId(LoggedID);
        Map<Products,Integer> MyProducts=new HashMap<>();
        for(CartItem item:MyCarts){
            MyProducts.put(item.getProduct(),item.getQuantity());
        }
        //System.out.println(MyCarts);
        model.addAttribute("fname",fname);
        model.addAttribute("myproducts",MyProducts);
       // model.addAttribute("cartid",MyCarts);
        return "usercarts";
    }
}
