package com.qsp.productcategory.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.productcategory.dto.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>{

}
