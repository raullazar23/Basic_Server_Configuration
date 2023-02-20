package com.example.postgresql.controller;

import com.example.postgresql.model.Orders;
import com.example.postgresql.model.Restaurant;
import com.example.postgresql.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping({"/restaurant"})
public class CRUDRestaurant {

    private final RestaurantRepository restaurantRepository;

    public CRUDRestaurant(RestaurantRepository restaurantRepository, RestaurantRepository restaurantRepository1) {
        this.restaurantRepository = restaurantRepository1;
    }

    @GetMapping
    public ResponseEntity getAllRestaurants() {
        return ResponseEntity.ok(restaurantRepository.findAll());
    }

    //ID search
    @GetMapping("/{restaurantID}")
    public ResponseEntity findByIdRestaurant(@PathVariable Long restaurantID) {
        return ResponseEntity.ok(restaurantRepository.findById(restaurantID));
    }

    //Total Delete/ID delete
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        restaurantRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/all")
    public ResponseEntity delete() {

        restaurantRepository.deleteAll();
        return ResponseEntity.ok("rip");
    }


    //Data Insert restaurant/room
    @PostMapping
    public ResponseEntity InsertDataRestaurant(@RequestBody Restaurant restaurant) {

        if (restaurant.getAddress() == null || restaurant.getManager() == null) {
            return ResponseEntity.badRequest().body("Not enough info Points");
        }

        Restaurant savedrestaurant = restaurantRepository.save(restaurant);

        if (savedrestaurant != null) {
            return ResponseEntity.ok(restaurant.getId());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
        }

    }


    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable long id) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);

        if (!restaurantOptional.isPresent())
            return ResponseEntity.notFound().build();

        restaurant.setId((int) restaurant.getId());
        restaurant.setAddress(restaurant.getAddress());
        restaurant.setManager(restaurant.getManager());
        restaurantRepository.save(restaurant);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{searchTerm}")
    public List<Restaurant> findByTerm(@PathVariable String searchTerm){
    return restaurantRepository.findAllBySearchTerm(searchTerm);
}


}
