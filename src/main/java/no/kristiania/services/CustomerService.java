package no.kristiania.services;

import no.kristiania.repositories.customers.Customer;
import no.kristiania.repositories.customers.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

   @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public Customer getCustomerById(Long id){
        return customerRepo.findById(id).orElse(null);
    }

    public void deleteCustomer(Long id) {
        customerRepo.deleteById(id);
    }

}
