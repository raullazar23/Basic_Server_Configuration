package com.example.postgresql.model;

import javax.persistence.*;

@Entity
@Table(name="Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;
    @Column(name="discipline")
    private String discipline;

    public Student() {
    }

    public Student(Long id, String name, String discipline) {
        this.id = id;
        this.name = name;
        this.discipline = discipline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String description) {
        this.discipline = description;
    }
}
