package com.testtask.caloric;

import com.testtask.caloric.model.ProductUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ProductUpdateRepository extends JpaRepository<ProductUpdate, Long> {
   List<ProductUpdate> findByProductId(Long productId);
}