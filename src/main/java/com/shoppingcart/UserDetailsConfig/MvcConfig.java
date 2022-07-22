package com.shoppingcart.UserDetailsConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/403").setViewName("403");
      //  registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login_success").setViewName("login_success");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path brandUploadDir= Paths.get("./brand-logos");
        String brandUploadPath=brandUploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/brand-logos/**").addResourceLocations("file:/"+brandUploadPath+"/");
    }
}
