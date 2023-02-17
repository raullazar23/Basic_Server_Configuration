package com.example.postgresql.model;

import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@NoArgsConstructor
@Table(name="room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nume")
    private String nr;

    @Column(name= "professor")
    private String professor;
    @Column(name="discipline")
    private String discipline;

    public Room(Long id, String nr, String professor, String discipline) {
        this.id = id;
        this.nr = nr;
        this.professor = professor;
        this.discipline = discipline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String proffesor) {
        this.professor = proffesor;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

}
