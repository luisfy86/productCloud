package com.luis.product.web;

import com.luis.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductClientController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/client/{id}")
    Product getProduct(@PathVariable("id") int id){
        Product product = restTemplate.getForObject("http://PRODUCT"+"/product/"+id, Product.class);
        return product;
    }
}
