package no.kristiania.services;

import no.kristiania.errors.CustomerNotFoundException;
import no.kristiania.errors.OrderNotFoundException;
import no.kristiania.repositories.customers.Customer;
import no.kristiania.repositories.customers.CustomerAddressRepo;
import no.kristiania.repositories.customers.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerAddressRepo customerAddressRepo;
   @Autowired
    public CustomerService(
            CustomerRepo customerRepo,
            CustomerAddressRepo customerAddressRepo
           ) {

       this.customerRepo = customerRepo;
       this.customerAddressRepo = customerAddressRepo;
    }

    // TODO: Fetching a customer should show their addresses and order history.


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
        if (!customerRepo.existsById(id)) {
            throw new CustomerNotFoundException("Customer not found for ID: " + id);
        }

       customerRepo.deleteById(id);
    }

    public void deleteAllCustomers() {
       customerAddressRepo.deleteAll();
       customerRepo.deleteAll();
    }

}
