package com.example.postgresql.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "points")
public class Points {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int number;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
