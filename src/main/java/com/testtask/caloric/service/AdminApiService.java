package com.testtask.caloric.service;

import com.testtask.caloric.controller.exception.EntityNotFoundException;
import com.testtask.caloric.model.Product;
import com.testtask.caloric.model.ProductUpdateOrder;
import com.testtask.caloric.repository.ProductRepository;
import com.testtask.caloric.repository.ProductUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminApiService implements IAdminApiService {

    @Autowired
    private ProductUpdateRepository productUpdateRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductUpdateOrder> findAllProductUpdateOrders() {
       return productUpdateRepository.findAll();
    }

    @Override
    public List<ProductUpdateOrder> findByProductId(Long productId) {
        return productUpdateRepository.findByProductId(productId);
    }

    @Override
    public ProductUpdateOrder findByProductUpdateOrderId(Long id) {
        ProductUpdateOrder productUpdateOrder = productUpdateRepository.findProductUpdateOrderById(id).map(entity -> entity)
                .orElseThrow(() -> new EntityNotFoundException(id));

        return productUpdateOrder;
    }

    @Override
    public ProductUpdateOrder doProcessProductUpdateOrder(Long id, boolean accept) {
        ProductUpdateOrder productUpdateOrder = productUpdateRepository.findProductUpdateOrderById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));

        if (accept) {

            //на всякий случай проверим, что продукт еще такой есть
            Product product = productRepository.findById(productUpdateOrder.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException(id));

            product.setName(productUpdateOrder.getName());
            product.setManufacturer(productUpdateOrder.getManufacturer());
            product.setCalories(productUpdateOrder.getCalories());
            product.setFats(productUpdateOrder.getFats());
            product.setProteins(productUpdateOrder.getProteins());
            product.setCarbohydrates(productUpdateOrder.getCarbohydrates());

            productRepository.save(product);

        }
                productUpdateOrder.setProcessed(true);

        return productUpdateRepository.save(productUpdateOrder);
    }

    @Override
    public Product doPublishProduct(Long id, boolean publish) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));

        product.setAviable(publish);

        return productRepository.save(product);

    }
}
