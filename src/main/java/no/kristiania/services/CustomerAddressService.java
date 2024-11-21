package no.kristiania.services;

import no.kristiania.repositories.customers.Customer;
import no.kristiania.repositories.customers.CustomerAddress;
import no.kristiania.repositories.customers.CustomerAddressRepo;
import no.kristiania.repositories.customers.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAddressService {
    private final CustomerAddressRepo customerAddressRepo;

@Autowired
    public CustomerAddressService(CustomerAddressRepo customerAddressRepo) {
        this.customerAddressRepo = customerAddressRepo;
    }

    public List<CustomerAddress> getAllCustomerAddresses() {
        return customerAddressRepo.findAll();
    }

    public CustomerAddress createCustomerAddress(CustomerAddress customerAddress) {
        return customerAddressRepo.save(customerAddress);
    }

    public CustomerAddress getCustomerAddressById(Long id){
        return customerAddressRepo.findById(id).orElse(null);
    }

    public void deleteCustomerAddress(Long id) {
        customerAddressRepo.deleteById(id);
    }

}
