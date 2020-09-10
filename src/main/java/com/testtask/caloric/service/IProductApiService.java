package com.testtask.caloric.service;

import com.testtask.caloric.dto.RestDTO;
import com.testtask.caloric.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductService {
//    List<Product> getAviableProductsList(int page, int size);
    List<Product> getAviableProductsList();

    Product findAviableProductById(Long id);

    List<Product> findAviableProductsByName(String name);

    Product save(Product newProduct);

    Product updateProduct(Long id, Product updatedProduct);
}
