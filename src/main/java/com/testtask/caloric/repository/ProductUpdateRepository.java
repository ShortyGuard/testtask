package com.testtask.caloric.repository;

import com.testtask.caloric.model.ProductUpdateOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий работы с сущностью запроса на изменение продукта
 */
@Repository
public interface ProductUpdateRepository extends JpaRepository<ProductUpdateOrder, Long>, PagingAndSortingRepository<ProductUpdateOrder, Long> {

   Page<ProductUpdateOrder> findByProductId(Long productId, Pageable pageReq);

   Optional<ProductUpdateOrder> findProductUpdateOrderById(Long id);
}