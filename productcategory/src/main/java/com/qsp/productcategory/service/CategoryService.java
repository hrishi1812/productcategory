package com.qsp.productcategory.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.qsp.productcategory.dto.Category;
import com.qsp.productcategory.repo.CategoryRepo;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo ;

    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepo.findAll(pageable);
    }

    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category createCategory(Category category) {
        if (category.getId() != null) {
            Optional<Category> existingCategory = categoryRepo.findById(category.getId());
            if (existingCategory.isPresent() && !existingCategory.get().equals(category)) {
                throw new RuntimeException("Category already exists or was modified");
            }
        }
        return categoryRepo.save(category);
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = getCategoryById(id);
        category.setName(categoryDetails.getName());
        return categoryRepo.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}
