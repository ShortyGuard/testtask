package com.testtask.caloric.service;

import com.testtask.caloric.model.Product;

import java.util.List;

/**
 * Интерфейс сервиса работы пользователя с продуктом
 */
public interface IProductApiService {

    List<Product> getAviableProductsList(int page, int size, String sortDir, String sort);

    List<Product> findAviableProductsByName(String name, int page, int size, String sortDir, String sort);

    Product findAviableProductById(Long id);

    Product save(Product newProduct);

    Product updateProduct(Long id, Product updatedProduct);

}
