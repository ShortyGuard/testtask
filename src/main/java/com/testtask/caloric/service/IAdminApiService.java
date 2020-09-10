package com.testtask.caloric.service;

import com.testtask.caloric.model.Product;
import com.testtask.caloric.model.ProductUpdateOrder;

import java.util.Arrays;
import java.util.List;

public interface IAdminApiService {
    List<ProductUpdateOrder> findAllProductUpdateOrders();

    List<ProductUpdateOrder> findByProductId(Long productId);

    ProductUpdateOrder findByProductUpdateOrderId(Long id);

    ProductUpdateOrder doProcessProductUpdateOrder(Long id, boolean accept);

    Product doPublishProduct(Long id, boolean publish);
}
