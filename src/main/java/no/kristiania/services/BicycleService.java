package no.kristiania.services;

import no.kristiania.domain.Customer;
import no.kristiania.repository.BicycleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //@Service represents this class is service layer.
public class BicycleService {

    @Autowired
    private BicycleRepo bicycleRepo;

    public BicycleService(BicycleRepo bicycleRepo) {this.bicycleRepo = bicycleRepo;}

    //Read
    public List<Customer> getAllBicycles() {
        return bicycleRepo.findAll();
    }

    //Create
    public Customer createBicycle(Customer customer) {
        return bicycleRepo.save(customer);
    }

    //Update
    public Customer getBicycleById(Long id) {
        return bicycleRepo.findById(id).orElse(null);
    }

    //Delete
    public void deleteBicycle(Long id) {
        bicycleRepo.deleteById(id);
    }

}
