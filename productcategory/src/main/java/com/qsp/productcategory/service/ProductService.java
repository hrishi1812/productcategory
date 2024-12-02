package com.qsp.productcategory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.qsp.productcategory.dto.Category;
import com.qsp.productcategory.dto.Product;
import com.qsp.productcategory.repo.CategoryRepo;
import com.qsp.productcategory.repo.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private CategoryRepo categoryRepository;

    
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

        public Product createProduct(Product product, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));
        product.setCategory(category);
        return productRepository.save(product);
    }

    
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());

        
        if (updatedProduct.getCategory() != null) {
            Long categoryId = updatedProduct.getCategory().getId();
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));
            existingProduct.setCategory(category);
        }

        return productRepository.save(existingProduct);
    }

    
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
