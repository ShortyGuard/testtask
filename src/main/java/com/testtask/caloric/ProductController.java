package com.testtask.caloric;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ProductController {

    private final ProductRepository repository;

    ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    /**
     * Возвращает все доступные продукты
     */
    @GetMapping("/products")
    List<Product> all() {
        return repository.findByIsAviable(true);
    }

    /**
     * Вохвращает все продукты по нечеткому поиску по полю имени
     */
    @GetMapping("/search/{name}")
    List<Product> searchByName(@PathVariable String name) {

        return repository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Создание нового продукта
     */
    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct) {
        return repository.save(newProduct);
    }

    /**
     * Для администратора: опудликование или сокрытите продукта
     * action = {PUBLISH|HIDE}
     */
    @PostMapping("/products/{id}/action")
    Product actionProduct(@PathVariable Long id, @PathVariable String action) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        switch (action)
        {
            case "PUBLISH":
            {
                product.setAviable(true);
                break;
            }
            case "HIDE":
            {
                product.setAviable(false);
                break;
            }
        }
        return repository.save(product);
    }

    /**
     * Получение продукта по идентификатору
     */
    @GetMapping("/products/{id}")
    Product one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    /**
     * Удаление продукта по идентификатору
     */
    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
    }
}