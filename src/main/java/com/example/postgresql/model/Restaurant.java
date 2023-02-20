package com.example.postgresql.model;


import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="restaurant")
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="address")
    private String address;
    @Column(name="manager")
    private String manager;
    @ManyToMany(mappedBy = "restaurants")
    private Set<Customer> customers=new HashSet<>();

    public Restaurant(long id, String address, String manager) {
        this.id = id;
        this.address = address;
        this.manager = manager;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

}
