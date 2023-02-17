package com.example.postgresql.repository;

import com.example.postgresql.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    @Query("Select p from Student p WHERE concat( p.id, p.name) Like %:searchTerm%")
    List<Student> findAllBySearchTerm(@Param("searchTerm") String searchTerm);


}

