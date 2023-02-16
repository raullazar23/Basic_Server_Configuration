package com.example.postgresql.repository;

import com.example.postgresql.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("Select p from Product p WHERE concat( p.id, p.name, p.description) Like %:searchTerm%")
    List<Product> findAllBySearchTerm(@Param("searchTerm") String searchTerm);
}
