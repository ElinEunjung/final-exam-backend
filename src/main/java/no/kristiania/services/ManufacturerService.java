package no.kristiania.services;

import no.kristiania.domain.CustomerAddress;
import no.kristiania.repository.ManufacturerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerService {
    private final ManufacturerRepo manufacturerRepo;


    public ManufacturerService(ManufacturerRepo manufacturerRepo) {
        this.manufacturerRepo = manufacturerRepo;
    }

    public List<CustomerAddress> getAllManufacturers() {
        return manufacturerRepo.findAll();
    }

    public CustomerAddress createManufacturer(CustomerAddress customerAddress) {
        return manufacturerRepo.save(customerAddress);
    }

    public CustomerAddress getManufacturerById(Long id){
        return manufacturerRepo.findById(id).orElse(null);
    }

    public void deleteManufacturer(Long id) {
        manufacturerRepo.deleteById(id);
    }

}
