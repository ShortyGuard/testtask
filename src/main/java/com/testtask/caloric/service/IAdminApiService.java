package com.testtask.caloric.service;

import com.testtask.caloric.model.Product;
import com.testtask.caloric.model.ProductUpdateOrder;

import java.util.List;

/**
 * Интерфейс сервиса работы администратора
 */
public interface IAdminApiService {
    List<ProductUpdateOrder> findAllProductUpdateOrders(int page, int size, String sortDir, String sort);

    List<ProductUpdateOrder> findByProductId(Long productId, int page, int size, String sortDir, String sort);

    ProductUpdateOrder findByProductUpdateOrderId(Long id);

    ProductUpdateOrder doProcessProductUpdateOrder(Long id, boolean accept);

    Product doPublishProduct(Long id, boolean publish);
}
