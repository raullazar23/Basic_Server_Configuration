package com.example.postgresql.repository;

import com.example.postgresql.model.Customer;
import com.example.postgresql.model.Points;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointsRepository extends JpaRepository<Points, Long>{
    @Query("Select p from Points p WHERE concat( cast(p.id as text), cast(p.number as text), p.customer.name) Like %:searchTerm%")
    List<Points> findAllBySearchTerm(@Param("searchTerm") String searchTerm);


}

