package com.example.postgresql.repository;

import com.example.postgresql.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query("Select p from Orders p WHERE concat( p.id, p.number, p.customer.name,p.restaurant.address, p.description) LIKE %:searchTerm%")
    List<Orders> findAllBySearchTerm(@Param("searchTerm") String searchTerm);


}
