package com.example.postgresql.repository;

import com.example.postgresql.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("Select p from Room p WHERE concat( p.id, p.discipline,p.nr) LIKE %:searchTerm%")
    List<Room> findAllBySearchTerm(@Param("searchTerm") String searchTerm);


}
