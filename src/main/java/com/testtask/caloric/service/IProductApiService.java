package com.testtask.caloric.service;

import com.testtask.caloric.model.Product;

import java.util.List;

public interface IProductApiService {
    List<Product> getAviableProductsList();
    List<Product> getAviableProductsList(int page, int size, String sortDir, String sort);

    Product findAviableProductById(Long id);

    List<Product> findAviableProductsByName(String name);

    Product save(Product newProduct);

    Product updateProduct(Long id, Product updatedProduct);

}
