package com.example.postgresql.model;


import javax.persistence.*;

@Entity
@Table(name="Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nume")
    private String nr;

    @Column(name= "profesor")
    private String proffesor;

    @Column(name="discipline")
    private String discipline;

    public Room(Long id, String nr, String proffesor, String discipline) {
        this.id = id;
        this.nr = nr;
        this.proffesor = proffesor;
    }

    public Room() {

    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
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

    public void setNr(String name) {
        this.nr = name;
    }

    public String getProffesor() {
        return proffesor;
    }

    public void setProffesor(String proffesor) {
        this.proffesor = proffesor;
    }

}
