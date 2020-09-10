package com.testtask.caloric.repository;

import com.testtask.caloric.model.Product;
import com.testtask.caloric.model.ProductUpdateOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductUpdateRepository extends JpaRepository<ProductUpdateOrder, Long> {

   List<ProductUpdateOrder> findByProductId(Long productId);

   Optional<ProductUpdateOrder> findProductUpdateOrderById(Long id);
}