package com.example.postgresql.controller;

import com.example.postgresql.model.Points;
import com.example.postgresql.repository.PointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping({"/points"})
public class CRUDPoints {
    @Autowired
    private PointsRepository pointsRepository;

//    public CRUDPoints(PointsRepository pointsRepository) {
//        this.pointsRepository = pointsRepository;
//    }

    @GetMapping
    public ResponseEntity getAllProducts() {
        return ResponseEntity.ok(pointsRepository.findAll());
    }

    //ID search
    @GetMapping("/{id}")
    public ResponseEntity findByIdPoints(@PathVariable Long id) {
        return ResponseEntity.ok(pointsRepository.findById(id));
    }

    //Total Delete/ID delete
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        pointsRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/all")
    public ResponseEntity delete() {

        pointsRepository.deleteAll();
        return ResponseEntity.ok("rip");
    }


    //Data Insert points/room
    @PostMapping
    public ResponseEntity InsertDataPoints(@RequestBody Points points) {

        if (points.getNumber()<0 ) {
            return ResponseEntity.badRequest().body("Not enough info Points");
        }

        Points savedpoints = pointsRepository.save(points);


        if (savedpoints != null) {
            return ResponseEntity.ok(points.getId());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
        }

    }


    //Update
    @PutMapping("/{id}")
    public ResponseEntity Update(@PathVariable Long id,@RequestBody Points newpoints)
    {
        if (newpoints==null){
            return ResponseEntity.ok("Nu merge");
        }else{
        Optional<Points> points = pointsRepository.findById(id);
        Points existingPoints = points.get();

        existingPoints.setNumber(newpoints.getNumber());
        existingPoints.setCustomer(newpoints.getCustomer());


        pointsRepository.save(existingPoints);

        return ResponseEntity.ok("Done I guess");
    }
    }

    @GetMapping("/term/{searchTerm}")
    public List<Points> findByTerm(@PathVariable String searchTerm){
    return pointsRepository.findAllBySearchTerm(searchTerm);
}


}
