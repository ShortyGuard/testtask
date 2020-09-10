package com.testtask.caloric.controller;

import com.testtask.caloric.service.EntityNotFoundException;
import com.testtask.caloric.repository.ProductRepository;
import com.testtask.caloric.repository.ProductUpdateRepository;
import com.testtask.caloric.model.Product;
import com.testtask.caloric.model.ProductUpdate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class ProductUpdateController {

    private final ProductUpdateRepository repository;
    private final ProductRepository productRepository;

    ProductUpdateController(ProductRepository productRepository, ProductUpdateRepository repository) {
        this.repository = repository;
        this.productRepository  = productRepository;
    }

    /**
     * Для администратора: Возвращает все запросы на изменение продуктов
     */
    @GetMapping("/productUpdates")
    List<ProductUpdate> all() {
        return repository.findAll();
    }


    /**
     * Для администратора: Возвращает все запросы на изменение продукта по идентификатору продукта
     */
    @GetMapping("/productUpdates/search/{productId}")
    List<ProductUpdate> searchByName(@PathVariable Long productId) {

        return repository.findByProductId(productId);
    }

    /**
     * Для администратора: Возвращает запрос на изменение продукта по идентификатору
     */
    @GetMapping("/productUpdates/{id}")
    ProductUpdate one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    /**
     * Создает от пользователя запрос на изменение продукта по идентификатору.
     * ИДЕНТИФИКАТОР можно внести в тело запроса, но сделал так
     */
/*    @PutMapping("/products/{id}")
    ProductUpdate addProductUpdate(@RequestBody ProductUpdate newProductUpdate, @PathVariable Long id) {

        newProductUpdate.setProductId(id);
        return repository.save(newProductUpdate);
    }*/

    /**
     * Для администратора: Принятие решения о публикации изменения.
     * В случае принятия ACCEPT - произведется автоматическое копирование новых атрибутов в продукт
     * action == {ACCEPT|DENY}
     * Проставится флаг обработки запроса
     */
    @PostMapping("/productUpdates/{id}/{action}")
    ProductUpdate replaceEmployee(@PathVariable Long id, @PathVariable String action) {

        ProductUpdate productUpdate = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));

        switch (action)
        {
            case "ACCEPT":
            {
                //update product
                Product product = productRepository.findById(productUpdate.getProductId())
                        .orElseThrow(() -> new EntityNotFoundException(id));

                product.setName(productUpdate.getName());
                product.setManufacturer(productUpdate.getManufacturer());
                product.setCalories(productUpdate.getCalories());
                product.setFats(productUpdate.getFats());
                product.setProteins(productUpdate.getProteins());
                product.setCarbohydrates(productUpdate.getCarbohydrates());

                productRepository.save(product);

                productUpdate.setProcessed(true);
                break;
            }
            case "DENY":
            {
                productUpdate.setProcessed(true);
                break;
            }
        }

        return repository.save(productUpdate);
    }


}