package com.luis.product.web;

import com.luis.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.luis.product.service.ProductService;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @Value("${testMessage:Hello default}")
    private String message;

    @RequestMapping("/product/{id}")
    Product getProduct(@PathVariable("id")Integer id){
        return productService.getProduct(id);
    }

    @RequestMapping("/products")
    List<Product> getProductsForCategory(@RequestParam("id")int id){
        return productService.getProductsForCategory(id);
    }

    @RequestMapping(value="/product", method = RequestMethod.POST)
    ResponseEntity<Product> insertProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product) ;
        return new ResponseEntity<Product>(savedProduct, HttpStatus.OK);
    }

    @RequestMapping(value="/product/{id}", method = RequestMethod.PUT)
    ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id,
                                          @RequestBody Product product) {
        return new ResponseEntity<Product>(productService.updateProduct(product, id), HttpStatus.OK);
    }

    @RequestMapping(value="/product/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Product> deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return new ResponseEntity<Product>(HttpStatus.OK);
    }

    @RequestMapping(value="/testMessage")
    String getTestMessage(){
        return message;
    }
}
