package com.testtask.caloric.controller;

import com.testtask.caloric.controller.exception.UndefinedActionException;
import com.testtask.caloric.dto.ProductDTO;
import com.testtask.caloric.dto.ProductUpdateOrderDTO;
import com.testtask.caloric.model.Product;
import com.testtask.caloric.model.ProductUpdateOrder;
import com.testtask.caloric.service.IAdminApiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер обработки запросов от администратора
 */
@RestController
@RequestMapping("/admin")
class AdminApiController {

    @Autowired
    private IAdminApiService adminApiService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Для администратора: Возвращает все запросы на изменение продуктов
     * При передаче параметра productId возвращает все запросы на изменение продуктов для переданного идентификатора продукта
     */
    @GetMapping("/productUpdateOrders")
    List<ProductUpdateOrderDTO.ResponseProductUpdateOrder.Private> getAllProductUpdateOrders(
            @RequestParam(name = "productId", required = false) Long productId,
            @Valid PageParams pageParams) {
        if (productId == null) {
            return adminApiService.findAllProductUpdateOrders(pageParams.getPage(),
                    pageParams.getSize(),
                    pageParams.getSortDir(),
                    pageParams.getSort()).stream()
                    .map(this::convertToProductUpdateOrderDto)
                    .collect(Collectors.toList());
        }

        return adminApiService.findByProductId(productId,
                pageParams.getPage(),
                pageParams.getSize(),
                pageParams.getSortDir(),
                pageParams.getSort()).stream()
                .map(this::convertToProductUpdateOrderDto)
                .collect(Collectors.toList());
    }

    /**
     * Для администратора: Возвращает запрос на изменение продукта по идентификатору
     */
    @GetMapping("/productUpdateOrders/{id}")
    ProductUpdateOrderDTO.ResponseProductUpdateOrder.Private one(@PathVariable Long id) {

        return convertToProductUpdateOrderDto(adminApiService.findByProductUpdateOrderId(id));
    }

    /**
     * Для администратора: Принятие решения о публикации изменения.
     * В случае принятия ACCEPT - произведется автоматическое копирование новых атрибутов в продукт
     * action == {ACCEPT|DENY}
     * Проставится флаг обработки запроса
     */
    @PostMapping("/productUpdateOrders/{id}/{action}")
    ProductUpdateOrderDTO.ResponseProductUpdateOrder.Private processProductUpdateOrder(@PathVariable Long id,
                                                                                       @Valid @NotBlank @PathVariable String action) {

        if (action.equals("ACCEPT")) {
            return convertToProductUpdateOrderDto(adminApiService.doProcessProductUpdateOrder(id, true));
        } else if (action.equals("DENY")) {
            return convertToProductUpdateOrderDto(adminApiService.doProcessProductUpdateOrder(id, false));
        }

        throw new UndefinedActionException("action is not defined. Use: ACCEPT|DENY");
    }

    /**
     * Для администратора: Принятие решения о публикации продукта.
     * В случае принятия PUBLISH - произведется выставление флага is_aviable у продукта в true
     * В случае принятия HIDE - произведется выставление флага is_aviable у продукта в false
     * action == {PUBLISH|HIDE}
     */
    @PostMapping("/products/{id}/{action}")
    ProductDTO.ResponseProduct.Private publishProduct(@PathVariable Long id,
                                                      @Valid @NotBlank @PathVariable String action) {

        if (action.equals("PUBLISH")) {
            return convertToProductPrivateDto(adminApiService.doPublishProduct(id, true));
        } else if (action.equals("HIDE")) {
            return convertToProductPrivateDto(adminApiService.doPublishProduct(id, false));
        }

        throw new UndefinedActionException("action is not defined. Use: PUBLISH|HIDE");
    }

    /**
     * функция конвертации сущности в нужный DTO
     */
    private ProductDTO.ResponseProduct.Private convertToProductPrivateDto(Product product) {

        return modelMapper.map(product, ProductDTO.ResponseProduct.Private.class);
    }

    /**
     * функция конвертации сущности в нужный DTO
     */
    private ProductUpdateOrderDTO.ResponseProductUpdateOrder.Private convertToProductUpdateOrderDto(ProductUpdateOrder productUpdateOrder) {
        return modelMapper.map(productUpdateOrder, ProductUpdateOrderDTO.ResponseProductUpdateOrder.Private.class);
    }
}