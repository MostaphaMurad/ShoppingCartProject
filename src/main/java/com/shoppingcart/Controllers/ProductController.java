package com.shoppingcart.Controllers;

import com.shoppingcart.Services.CartItemServices;
import com.shoppingcart.Models.CartItem;
import com.shoppingcart.Models.Products;
import com.shoppingcart.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({"/prod","/product","/products"})
public class ProductController {
    @Autowired
    private ProductServices ProdServ;
    @Autowired
    private CartItemServices cartServ;
    @RequestMapping("/allproduct")
    public String allProducts(Model model){
        List<Products> products=ProdServ.getAllProducts();
        model.addAttribute("products",products);
        return "allproducts";
    }


    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addProduct(Model model){
        model.addAttribute("product",new Products());
        return "addproduct";
    }
    @RequestMapping(value = "/saveprod",method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product")Products product, @RequestParam("fileImage")MultipartFile multipartFile) throws IOException {
        String FileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setImage(FileName);
        Products psaved=ProdServ.saveProduct(product);
        String uploadDir="./brand-logos/"+psaved.getBrand_id();
        Path uploadPath= Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try(InputStream inputStream=multipartFile.getInputStream()) {
            Path filepath=uploadPath.resolve(FileName);
            System.out.println(filepath.toFile().getAbsolutePath());
            Files.copy(inputStream, filepath,StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw  new IOException("could not save file name "+ FileName);
        }
        if(psaved!=null){
            return "redirect:/prod/allproduct";
        }
        else return "errorpage";
    }
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String EditProduct(@PathVariable("id")int id,Model model){
        Products p=ProdServ.getProductById(id);
        if(p!=null){
            model.addAttribute("product",p);
            return "addproduct";
        }
        else return "errorpage";
    }
    @RequestMapping(value = "/delete/{id}")
    public String deleteProduct(@PathVariable("id")int id){
        Products product=ProdServ.getProductById(id);
        List<CartItem>items=new ArrayList<>();
         items=cartServ.findCartsWithProductId(id);
        boolean delCart=cartServ.delCartItemWithProdId(items);
        if(delCart){
            boolean deleted=ProdServ.delProduct(id);
            return "redirect:/prod/allproduct";
        }
        else return "errorpage";
    }
}
