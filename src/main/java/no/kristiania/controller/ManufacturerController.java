package no.kristiania.controller;

import no.kristiania.domain.CustomerAddress;
import no.kristiania.services.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //REST API Controller. All method response with JSON form
@RequestMapping("/api/manufacturer") //Handle HTTP requests with the corresponding path
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @Autowired //Constructor Injection
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping //Mapping Read method
    public ResponseEntity<List<CustomerAddress>> getAllManufacturers() {
        List<CustomerAddress> customerAddress = manufacturerService.getAllManufacturers();
        return new ResponseEntity<>(customerAddress, HttpStatus.OK);
    }

    @PostMapping //Mapping Create method
    public ResponseEntity<CustomerAddress> createManufacturer(@RequestBody CustomerAddress customerAddress) {
        return new ResponseEntity<>(manufacturerService.createManufacturer(customerAddress), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}") //Mapping Read Method
    public ResponseEntity<CustomerAddress> getManufacturerById(@PathVariable Long id) {
        CustomerAddress customerAddress = manufacturerService.getManufacturerById(id);
        return new ResponseEntity<>(customerAddress, HttpStatus.OK);
    }

    @DeleteMapping("/{id}") //Mapping Delete method
    public ResponseEntity<?> deleteManufacturer(@PathVariable Long id) {
        manufacturerService.deleteManufacturer(id);
        return new ResponseEntity<>("Your manufacturer is deleted", HttpStatus.OK);
    }

   }
