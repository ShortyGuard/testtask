package com.testtask.caloric.controller;

import com.testtask.caloric.EntityNotFoundException;
import com.testtask.caloric.dto.RestDTO;
import com.testtask.caloric.model.Product;
import com.testtask.caloric.repository.ProductRepository;
import com.testtask.caloric.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
class ApiController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/products")
    @ResponseBody
    public List<RestDTO.ProductDTO.ResponseProduct.Basic> getProducts() {

        List<Product> products = productService.getAviableProductsList();
        return products.stream()
                .map(this::convertToProductBasicDto)
                .collect(Collectors.toList());
    }
/*
    public List<RestDTO.ProductDTO.ResponseProduct.Basic> getProducts(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        System.out.println("page = "+ page);
        System.out.println("size = "+ size);

        List<Product> products = productService.getAviableProductsList(page, size);
        return products.stream()
                .map(this::convertToProductBasicDto)
                .collect(Collectors.toList());
    }
*/

    private RestDTO.ProductDTO.ResponseProduct.Basic convertToProductBasicDto(Product product) {
        RestDTO.ProductDTO.ResponseProduct.Basic productBasicDto = modelMapper.map(product, RestDTO.ProductDTO.ResponseProduct.Basic.class);

        return productBasicDto;
    }

}