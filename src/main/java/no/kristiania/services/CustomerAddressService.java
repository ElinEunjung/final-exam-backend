package no.kristiania.services;

import no.kristiania.exceptions.CustomerAddressNotFoundException;
import no.kristiania.repositories.customers.CustomerAddress;
import no.kristiania.repositories.customers.CustomerAddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<String> getCustomerAddressByCustomerId(Long customerId){
        List<CustomerAddress> customerAddresses = customerAddressRepo.findCustomerAddressByCustomerId(customerId);
        if (customerAddresses.isEmpty()) {
            throw new CustomerAddressNotFoundException("Could not find any customer address for customer with id " + customerId);
        }
        return customerAddresses
                .stream()
                .map(CustomerAddress::getAddress)
                .collect(Collectors.toList());
    }

    public void deleteCustomerAddress(Long id) {
        if (!customerAddressRepo.existsById(id)) {
            throw new CustomerAddressNotFoundException("Customer Address not found for ID: " + id);
        }

    customerAddressRepo.deleteById(id);
    }

    public void deleteAllCustomerAddresses() {
        customerAddressRepo.deleteAll();
    }

}
