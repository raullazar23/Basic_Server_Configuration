package com.example.postgresql.controller;

import com.example.postgresql.model.Room;
import com.example.postgresql.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/room")
public class CRUDroom {
    @Autowired
    private final RoomRepository roomRepository;


    public CRUDroom(RoomRepository RoomRepository, RoomRepository RoomRepository1) {
        this.roomRepository = RoomRepository1;
    }


    @GetMapping
    public ResponseEntity getAllRooms() {
        return ResponseEntity.ok(roomRepository.findAll());
    }


    //ID search Room/Student
    @GetMapping("/room/{roomID}")
    public ResponseEntity findByIdRoom(@PathVariable Long roomID) {
        return ResponseEntity.ok(roomRepository.findById(roomID));
    }

    //Data Insert room

    @PostMapping
    public ResponseEntity InsertDataRoom(@RequestBody Room room) {

        if (room.getId()==null || room.getDiscipline() == null || room.getNr() == null || room.getProffesor() == null) {
            return ResponseEntity.badRequest().body("Not enough info");
        }

        Room savedRoom = roomRepository.save(room);

        if (savedRoom != null) {
            //return ResponseEntity.ok(room.getId());
            return ResponseEntity.ok("Yay");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
        }
    }


    //Update student/room
    @PutMapping("/room/{id}")
    public ResponseEntity UpdateRoom(@PathVariable Long id, @RequestBody Room newroom) {

        Optional<Room> room = roomRepository.findById(id);
        Room existingRoom = room.get();


        existingRoom.setDiscipline(newroom.getDiscipline());
        existingRoom.setNr(newroom.getNr());
        existingRoom.setProffesor(newroom.getProffesor());


        roomRepository.save(existingRoom);

        return ResponseEntity.ok("Done I guess");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        roomRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/all")
    public ResponseEntity delete() {

        roomRepository.deleteAll();
        return ResponseEntity.ok("rip");
    }


}
