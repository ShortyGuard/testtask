package com.testtask.caloric.repository;

import com.testtask.caloric.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий работы с сущностью продукта
 */
@Repository
public interface ProductRepository  extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {

    List<Product> findByIsAviable(boolean isAviable);
    Page<Product> findByIsAviable(boolean isAviable, Pageable pageReq);

    Optional<Product> findProductByIdAndIsAviable(Long id, boolean isAviable);

    List<Product> findAviableProductByNameContainingIgnoreCaseAndIsAviable(String name, boolean isAviable);
}