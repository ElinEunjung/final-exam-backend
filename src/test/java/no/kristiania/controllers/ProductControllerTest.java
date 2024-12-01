package no.kristiania.controllers;

import no.kristiania.repositories.products.Product;
import no.kristiania.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //REST API Controller. All method response with JSON form
@RequestMapping("/api/product") //Handle HTTP requests with the corresponding path
public class ProductControllerTest {
    private final ProductService productService;

    @Autowired //Constructor Injection
    public ProductControllerTest(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping //Mapping Read method
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> customerAddress = productService.getAllProducts();
        return new ResponseEntity<>(customerAddress, HttpStatus.OK);
    }

    @PostMapping //Mapping Create method
    public ResponseEntity<Product> createProduct(@RequestBody Product customerAddress) {
        return new ResponseEntity<>(productService.createProduct(customerAddress), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}") //Mapping Read Method
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product customerAddress = productService.getProductById(id);
        return new ResponseEntity<>(customerAddress, HttpStatus.OK);
    }

    @DeleteMapping("/{id}") //Mapping Delete method
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Your product is deleted", HttpStatus.OK);
    }

   }
