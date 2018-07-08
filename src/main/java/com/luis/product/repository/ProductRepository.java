package com.luis.product.repository;

import com.luis.product.model.Product;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.repository.Repository;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ProductRepository extends CrudRepository<Product, Integer> { //MongoDB repository
//public interface ProductRepository extends ElasticsearchRepository<Product, String> {
    List<Product> findByCatId(int catId);
}
