package com.example.postgresql.controller;

import com.example.postgresql.model.Customer;
import com.example.postgresql.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping({"/customer"})
public class CRUDCustomer {

    private final CustomerRepository customerRepository;

    public CRUDCustomer(CustomerRepository customerRepository, CustomerRepository customerRepository1) {
        this.customerRepository = customerRepository1;
    }

    @GetMapping
    public ResponseEntity getAllProducts() {
        return ResponseEntity.ok(customerRepository.findAll());
    }

    //ID search Orders/Customer
    @GetMapping("/{customerID}")
    public Customer findByIdCustomer(@PathVariable Long customerID) {
//        return ResponseEntity.ok(customerRepository.findById(customerID));
        Optional<Customer> customer= customerRepository.findById(customerID);
        return customer.get();

    }

    //Total Delete/ID delete
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        customerRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/all")
    public ResponseEntity delete() {

        customerRepository.deleteAll();
        return ResponseEntity.ok("rip");
    }


    //Data Insert customer/room
    @PostMapping
    public ResponseEntity InsertDataCustomer(@RequestBody Customer customer) {

        if (customer.getName() == null ) {
            return ResponseEntity.badRequest().body("Not enough info Customer");
        }

        Customer savedcustomer = customerRepository.save(customer);

        if (savedcustomer != null) {
            return ResponseEntity.ok(customer.getId());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
        }

    }


    //Update customer
    @PutMapping("/{id}")
    public ResponseEntity Update(@PathVariable Long id,@RequestBody Customer newcustomer)
    {
        Optional<Customer> customer = customerRepository.findById(id);
        Customer existingCustomer = customer.get();

        existingCustomer.setName(newcustomer.getName());

        customerRepository.save(existingCustomer);

        return ResponseEntity.ok("Done I guess");
    }

    @GetMapping("/term/{searchTerm}")
    public List<Customer> findByTerm(@PathVariable String searchTerm){
    return customerRepository.findAllBySearchTerm(searchTerm);
}


}
