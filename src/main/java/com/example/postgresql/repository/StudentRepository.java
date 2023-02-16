package com.example.postgresql.repository;

import com.example.postgresql.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    @Query("Select p from Student p WHERE concat( p.id, p.name, p.discipline) Like %:searchTerm%")
    List<Student> findAllBySearchTerm(@Param("searchTerm") String searchTerm);
}

