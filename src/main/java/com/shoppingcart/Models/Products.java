package com.shoppingcart.Models;

import javax.persistence.*;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,length = 45)
    private String name;
    @Column(nullable = true,length = 100,name = "description")
    private String desc;
    @Column(nullable = true,length = 100)
    private String image;
    @Column(nullable = true)
    private int in_stock;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int brand_id;
    private int category_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIn_stock() {
        return in_stock;
    }

    public void setIn_stock(int in_stock) {
        this.in_stock = in_stock;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
    @Transient
    public String getLogoImage(){
        if(image==null)return null;
        else
        {
            return "/brand-logos/"+brand_id+"/"+image;
        }
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", image='" + image + '\'' +
                ", in_stock=" + in_stock +
                ", brand_id=" + brand_id +
                ", category_id=" + category_id +
                '}';
    }
}
