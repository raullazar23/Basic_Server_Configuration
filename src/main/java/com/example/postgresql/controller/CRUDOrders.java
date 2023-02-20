package com.example.postgresql.controller;

import com.example.postgresql.model.Orders;
import com.example.postgresql.repository.OrdersRepository;
import com.example.postgresql.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class CRUDOrders {
    @Autowired
    private final OrdersRepository ordersRepository;

    public CRUDOrders(OrdersRepository OrdersRepository, OrdersRepository ordersRepository1, RestaurantRepository restaurantRepository) {
        this.ordersRepository = ordersRepository1;
    }

    @GetMapping
    public ResponseEntity getAllOrders() {
        return ResponseEntity.ok(ordersRepository.findAll());
    }


    //ID search Orders/Customer
    @GetMapping("/{ordersID}")
    public ResponseEntity findByIdOrders(@PathVariable Long ordersID) {
        return ResponseEntity.ok(ordersRepository.findById(ordersID));
    }

    //Data Insert orders


    @PostMapping
    public Orders createOrder(@RequestBody Orders order) {
        // save the new order to the database
        return ordersRepository.save(order);
    }



    //Update order
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@RequestBody Orders order, @PathVariable long id) {
        Optional<Orders> orderOptional = ordersRepository.findById(id);

        if (!orderOptional.isPresent())
            return ResponseEntity.notFound().build();

        order.setId(id);
        order.setRestaurant(order.getRestaurant());
        order.setDescription(order.getDescription());
        order.setNumber(order.getNumber());
        order.setCustomer(order.getCustomer());

        ordersRepository.save(order);

        return ResponseEntity.noContent().build();
    }




    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        ordersRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/all")
    public ResponseEntity delete() {

        ordersRepository.deleteAll();
        return ResponseEntity.ok("rip");
    }


    @GetMapping("/search/{searchTerm}")
    public List<Orders> findByTerm(@PathVariable String searchTerm){
        return ordersRepository.findAllBySearchTerm(searchTerm);
    }
}
