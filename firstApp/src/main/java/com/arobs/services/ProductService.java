package com.arobs.services;

import com.arobs.entities.Product;
import com.arobs.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductService {

    public static List<Product> productsList= new ArrayList<>(7);



    public static  void populateProductsList(){
        Random rand = new Random();
        for(int i=0;i<7;i++){
            Product product= new Product("product_no"+i, rand.nextInt(100), rand.nextInt(50)*1.0);
            productsList.add(product);
        }
    }

    public static boolean addProduct(User user, Product product){

        int validation= validateQuantity(product);
       if(validation== -1 ) {
           return false;
       }
       int newQuantity= productsList.get(validation).getQuantity()- product.getQuantity();

       productsList.get(validation).setQuantity(newQuantity);

       product.setPrice(product.getQuantity() * productsList.get(validation).getPrice());
   //    if(user!=null)
       UserService.addProduct(user, product);
       return true;

    }

    public static int validateQuantity(Product addedProduct){
        String productName=addedProduct.getName();
        int productQuantity= addedProduct.getQuantity();
        for(int i=0;i<productsList.size();i++){
            Product product=productsList.get(i);
            if(product.getName().equals(productName)){
                if(product.getQuantity()>= productQuantity)
                {
                   return i;
                }
            }
        }
        return -1;
    }


}
