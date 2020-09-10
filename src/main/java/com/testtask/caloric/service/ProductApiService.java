package com.testtask.caloric.service;

import com.testtask.caloric.model.Product;
import com.testtask.caloric.model.ProductUpdate;
import com.testtask.caloric.repository.ProductRepository;
import com.testtask.caloric.repository.ProductUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductUpdateRepository productUpdateRepository;

    /*
        @Override
        public List<Product> getAviableProductsList(int page, int size) {
            PageRequest pageReq
                    = PageRequest.of(page, size);

            Page<Product> products = productRepository.findByIsAviable(true, pageReq);
            return products.getContent();
        }
    */
    @Override
    public List<Product> getAviableProductsList() {
        return productRepository.findByIsAviable(true);
    }

    @Override
    public Product findAviableProductById(Long id) {
        Product product = productRepository.findProductByIdAndIsAviable(id, true).map(entity -> entity)
                .orElseThrow(() -> new EntityNotFoundException(id));

        return product;
    }

    @Override
    public List<Product> findAviableProductsByName(String name) {
        return productRepository.findAviableProductByNameContainingIgnoreCaseAndIsAviable(name, true);
    }

    @Override
    public Product save(Product newProduct) {
        return productRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findProductByIdAndIsAviable(id, true).map(entity -> entity)
                .orElseThrow(() -> new EntityNotFoundException(id));

        updatedProduct.setId(id);

        ProductUpdate productUpdate = new ProductUpdate(
                id,
                product.getName(),
                product.getManufacturer(),
                product.getCalories(),
                product.getProteins(),
                product.getFats(),
                product.getCarbohydrates());

        productUpdateRepository.save(productUpdate);

        return updatedProduct;
    }


}
