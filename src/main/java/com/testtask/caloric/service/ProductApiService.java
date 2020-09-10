package com.testtask.caloric.service;

import com.testtask.caloric.controller.exception.EntityNotFoundException;
import com.testtask.caloric.model.Product;
import com.testtask.caloric.model.ProductUpdateOrder;
import com.testtask.caloric.repository.ProductRepository;
import com.testtask.caloric.repository.ProductUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductApiService implements IProductApiService {

    //TODO: заменить на интерфейс сервиса
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductUpdateRepository productUpdateRepository;

    @Override
    public List<Product> getAviableProductsList() {
        return productRepository.findByIsAviable(true);
    }

    @Override
    public List<Product> getAviableProductsList(int page, int size, String sortDir, String sort) {
        PageRequest pageReq
                = PageRequest.of(page - 1, size, Sort.Direction.fromString(sortDir), sort);

        Page<Product> products = productRepository.findByIsAviable(true, pageReq);
        return products.getContent();
    }

    @Override
    public Product findAviableProductById(Long id) {
        Product product = productRepository.findProductByIdAndIsAviable(id, true).map(entity -> entity)
                .orElseThrow(() -> new EntityNotFoundException(id));

        return product;
    }

    @Override
    public List<Product> findAviableProductsByName(String name) {
        return productRepository.findAviableProductByNameContainingIgnoreCaseAndIsAviable(name, true);
    }

    @Override
    public Product save(Product newProduct) {
        return productRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findProductByIdAndIsAviable(id, true).map(entity -> entity)
                .orElseThrow(() -> new EntityNotFoundException(id));

        updatedProduct.setId(id);

        ProductUpdateOrder productUpdateOrder = new ProductUpdateOrder(
                id,
                product.getName(),
                product.getManufacturer(),
                product.getCalories(),
                product.getProteins(),
                product.getFats(),
                product.getCarbohydrates());

        productUpdateRepository.save(productUpdateOrder);

        return updatedProduct;
    }


}
