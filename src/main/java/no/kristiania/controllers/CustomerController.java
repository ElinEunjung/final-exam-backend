package no.kristiania.controllers;

import no.kristiania.repositories.customers.Customer;
import no.kristiania.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //REST API Controller. All method response with JSON form
@RequestMapping("/api/customer") //Handle HTTP requests with the corresponding path
public class CustomerController {
    private final CustomerService customerService;

    @Autowired //Constructor Injection
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping //Mapping Read method
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

//    @GetMapping("/instock")
//    public ResponseEntity<List<Customer>> getInStockCustomers(){
//        List<Customer> customers = customerService.getAllCustomers();
//        List<Customer> inStockCustomers = customers
//                .stream()
//                .filter(customer -> customer.getNrInStock() > 0)
//                .toList();
//        return new ResponseEntity<>(inStockCustomers, HttpStatus.OK);
//    }
    @PostMapping //Mapping Create method
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}") //Mapping Read Method
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}") //Mapping Delete method
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("Your customer is deleted", HttpStatus.OK);
    }
}
