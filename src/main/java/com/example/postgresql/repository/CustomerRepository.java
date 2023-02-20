package com.example.postgresql.repository;

import com.example.postgresql.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    @Query("Select p from Customer p WHERE concat( p.id, p.name) Like %:searchTerm%")
    List<Customer> findAllBySearchTerm(@Param("searchTerm") String searchTerm);


}

