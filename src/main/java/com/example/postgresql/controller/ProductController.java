package com.example.postgresql.controller;

import com.example.postgresql.model.Product;
import com.example.postgresql.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;




    public ProductController(ProductRepository productRepository, ProductRepository productRepository1) {
        this.productRepository = productRepository1;
    }

    @GetMapping
    public ResponseEntity getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{productID}")
    public ResponseEntity findById(@PathVariable Long productID) {
        return ResponseEntity.ok(productRepository.findById(productID));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        productRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/all")
    public ResponseEntity delete() {

        productRepository.deleteAll();
        return ResponseEntity.ok("rip");
    }

    @PostMapping
    public ResponseEntity InsertData(@RequestBody Product product) {

        if (product.getName() == null || product.getDescription() == null) {
            return ResponseEntity.badRequest().body("Not enough info");
        }

        Product savedproduct = productRepository.save(product);

        if (savedproduct != null) {
            return ResponseEntity.ok(product.getId());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity Update(@PathVariable Long id,@RequestBody Product newproduct)
    {
        Optional<Product> product = productRepository.findById(id);
        Product existingProduct = product.get();

        existingProduct.setName(newproduct.getName());
        existingProduct.setDescription(newproduct.getDescription());

        productRepository.save(existingProduct);

        return ResponseEntity.ok("Done I guess");
    }

@GetMapping("/search/{searchTerm}")
    public List<Product> findByTerm(@PathVariable String searchTerm){
    return productRepository.findAllBySearchTerm(searchTerm);
}

}
