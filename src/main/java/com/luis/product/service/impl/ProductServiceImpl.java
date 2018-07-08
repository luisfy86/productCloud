package com.luis.product.service.impl;

import com.luis.product.model.Product;
import com.luis.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.luis.product.service.ProductService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMsgProducer producer;

    @Override
    public Product getProduct(Integer id) {
        return (productRepository.findById(id)).get();
    }

    @Override
    public List<Product> getProductsForCategory(int id) {
        return productRepository.findByCatId(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, Integer id) {
        Optional<Product> optional = productRepository.findById(id);
        Product existingProduct = optional.get();
        existingProduct.setCatId(product.getCatId());
        existingProduct.setName(product.getName());
        Product savedProduct = productRepository.save(existingProduct);
        return savedProduct;
    }

    @Override
    public void deleteProduct(Integer id) {
        Optional<Product> optional = productRepository.findById(id);
        Product existingProduct = optional.get();
        productRepository.deleteById(id);
        producer.sendUpdate(existingProduct, true);
    }
}
