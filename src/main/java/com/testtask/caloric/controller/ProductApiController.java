package com.testtask.caloric.controller;

import com.testtask.caloric.dto.ProductDTO;
import com.testtask.caloric.model.Product;
import com.testtask.caloric.service.IProductApiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер обработки запросов пользователей
 */
@RestController
@RequestMapping("/products")
class ProductApiController {

    @Autowired
    private IProductApiService productService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Запрашивает список опубликованных продуктов доступных для просмотра пользователям
     * При передаче параметра name происходит нечеткий поиск по имени товара
     *
     * @return весь список опубликованных продуктов в простом представлении по-странично
     */
    @GetMapping
    public List<ProductDTO.ResponseProduct.Basic> getProducts(
            @RequestParam(name = "name", required = false) String name,
            @Valid PageParams pageParams) {

        if (name == null) {
            List<Product> products = productService.getAviableProductsList(pageParams.getPage(),
                    pageParams.getSize(),
                    pageParams.getSortDir(),
                    pageParams.getSort());
            return products.stream()
                    .map(this::convertToProductBasicDto)
                    .collect(Collectors.toList());
        } else {
            List<Product> products = productService.findAviableProductsByName(name,
                    pageParams.getPage(),
                    pageParams.getSize(),
                    pageParams.getSortDir(),
                    pageParams.getSort());
            return products.stream()
                    .map(this::convertToProductBasicDto)
                    .collect(Collectors.toList());
        }
    }


    /**
     * Получение доступного продукта по идентификатору
     */
    @GetMapping("{id}")
    ProductDTO.ResponseProduct.Public one(@PathVariable Long id) {

        Product product = productService.findAviableProductById(id);

        return convertToProductPublicDto(product);
    }

    /**
     * Запрос на создание нового продукта
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO.ResponseProduct.Public createProduct(
            @Valid @RequestBody Product newProduct) {

        return convertToProductPublicDto(productService.save(newProduct));
    }


    /**
     * Создает от пользователя запрос на изменение продукта по идентификатору продукта.
     * ИДЕНТИФИКАТОР можно внести в тело запроса, но сделал так
     */
    @PutMapping("{id}")
    ProductDTO.ResponseProduct.Public addProductUpdate(@Valid @RequestBody Product updatedProduct, @PathVariable Long id) {

        return convertToProductPublicDto(productService.updateProduct(id, updatedProduct));
    }

    /**
     * функция конвертации сущности в нужный DTO
     */
    private ProductDTO.ResponseProduct.Basic convertToProductBasicDto(Product product) {

        return modelMapper.map(product, ProductDTO.ResponseProduct.Basic.class);
    }

    /**
     * функция конвертации сущности в нужный DTO
     */
    private ProductDTO.ResponseProduct.Public convertToProductPublicDto(Product product) {

        return modelMapper.map(product, ProductDTO.ResponseProduct.Public.class);
    }
}