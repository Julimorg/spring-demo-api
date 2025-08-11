package org.example.springdemoapi.Repository;

import org.example.springdemoapi.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}
