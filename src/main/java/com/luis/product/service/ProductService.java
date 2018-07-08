package com.luis.product.service;

import com.luis.product.model.Product;

import java.util.List;

public interface ProductService {

    Product getProduct(Integer id);

    List<Product> getProductsForCategory(int id);

    Product saveProduct(Product product);

    Product updateProduct(Product product, Integer id);

    void deleteProduct(Integer id);
}
