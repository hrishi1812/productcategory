package com.qsp.productcategory.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.productcategory.dto.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
