package com.example.postgresql.controller;

import com.example.postgresql.model.Student;
import com.example.postgresql.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping({"/student"})
public class CRUDstudent {

    private final StudentRepository studentRepository;


    public CRUDstudent(StudentRepository studentRepository, StudentRepository studentRepository1) {
        this.studentRepository = studentRepository1;
    }

    @GetMapping
    public ResponseEntity getAllProducts() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    //ID search Room/Student
    @GetMapping("/student/{studentID}")
    public ResponseEntity findByIdStudent(@PathVariable Long studentID) {
        return ResponseEntity.ok(studentRepository.findById(studentID));
    }

    //Total Delete/ID delete
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        studentRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/all")
    public ResponseEntity delete() {

        studentRepository.deleteAll();
        return ResponseEntity.ok("rip");
    }


    //Data Insert student/room
    @PostMapping
    public ResponseEntity InsertDataStudent(@RequestBody Student student) {

        if (student.getId() == null || student.getName() == null || student.getDiscipline() == null) {
            return ResponseEntity.badRequest().body("Not enough info Student");
        }

        Student savedstudent = studentRepository.save(student);

        if (savedstudent != null) {
            return ResponseEntity.ok("Yay");
            //return ResponseEntity.ok(student.getId());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
        }
    }


    //Update student/room
    @PutMapping("/student/{id}")
    public ResponseEntity Update(@PathVariable Long id,@RequestBody Student newstudent)
    {
        Optional<Student> student = studentRepository.findById(id);
        Student existingStudent = student.get();

        existingStudent.setName(newstudent.getName());
        existingStudent.setDiscipline(newstudent.getDiscipline());

        studentRepository.save(existingStudent);

        return ResponseEntity.ok("Done I guess");
    }

@GetMapping("/search/{searchTerm}")
    public List<Student> findByTerm(@PathVariable String searchTerm){
    return studentRepository.findAllBySearchTerm(searchTerm);
}

}
