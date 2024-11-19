package no.kristiania.controller;

import no.kristiania.domain.Customer;
import no.kristiania.services.BicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //REST API Controller. All method response with JSON form
@RequestMapping("/api/bicycle") //Handle HTTP requests with the corresponding path
public class BicycleController {
    private final BicycleService bicycleService;

    @Autowired //Constructor Injection
    public BicycleController(BicycleService bicycleService) {
        this.bicycleService = bicycleService;
    }

    @GetMapping //Mapping Read method
    public ResponseEntity<List<Customer>> getAllBicycles() {
        List<Customer> customers = bicycleService.getAllBicycles();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/instock")
    public ResponseEntity<List<Customer>> getInStockBicycles(){
        List<Customer> customers = bicycleService.getAllBicycles();
        List<Customer> inStockCustomers = customers
                .stream()
                .filter(customer -> customer.getNrInStock() > 0)
                .toList();
        return new ResponseEntity<>(inStockCustomers, HttpStatus.OK);
    }
    @PostMapping //Mapping Create method
    public ResponseEntity<Customer> createBicycle(@RequestBody Customer customer) {
        return new ResponseEntity<>(bicycleService.createBicycle(customer), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}") //Mapping Read Method
    public ResponseEntity<Customer> getBicycleById(@PathVariable Long id) {
        Customer customer = bicycleService.getBicycleById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}") //Mapping Delete method
    public ResponseEntity<?> deleteBicycle(@PathVariable Long id) {
        bicycleService.deleteBicycle(id);
        return new ResponseEntity<>("Your bicycle is deleted", HttpStatus.OK);
    }
}
