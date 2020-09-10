package com.testtask.caloric;

import com.testtask.caloric.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий работы с сущностью продукта
 */
interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByIsAviable(boolean isAviable);
    List<Product> findByNameContainingIgnoreCase(String name);
}