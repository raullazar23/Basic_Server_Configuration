package com.example.postgresql.repository;

import com.example.postgresql.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
    @Query("Select p from Restaurant p WHERE concat( p.id, p.address, p.manager) Like %:searchTerm%")
    List<Restaurant> findAllBySearchTerm(@Param("searchTerm") String searchTerm);


}

