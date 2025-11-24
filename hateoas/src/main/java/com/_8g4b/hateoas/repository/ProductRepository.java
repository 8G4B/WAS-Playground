package com._8g4b.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com._8g4b.hateoas.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
