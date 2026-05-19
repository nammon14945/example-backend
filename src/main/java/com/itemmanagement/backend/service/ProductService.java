package com.itemmanagement.backend.service;

import com.itemmanagement.backend.model.Product;
import com.itemmanagement.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product>getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> updateProduct(Long id, Product product) {
        Optional<Product> existing = productRepository.findById(id);

        if (existing.isEmpty()) {
            return Optional.empty();
        }

        Product existingProduct = existing.get();
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setInStock(product.getInStock());

        Product updatedProduct = productRepository.save(existingProduct);
        return Optional.of(updatedProduct);
    }

    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }

        productRepository.deleteById(id);
        return true;
    }

}
