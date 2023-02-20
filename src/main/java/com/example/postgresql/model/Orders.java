package com.example.postgresql.model;

import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name="orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name= "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    public Orders(Long id, String number, String description) {
        this.id = id;
        this.number = number;
        this.description = description;
        this.customer = customer;
        this.restaurant=restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String nr) {
        this.number = nr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String proffesor) {
        this.description = proffesor;
    }

}
