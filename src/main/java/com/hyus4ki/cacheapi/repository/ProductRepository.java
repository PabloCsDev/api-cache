package com.hyus4ki.cacheapi.repository;

import com.hyus4ki.cacheapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
