package no.kristiania.services;

import no.kristiania.dto.CustomerDTO;
import no.kristiania.dto.OrderDTO;
import no.kristiania.exceptions.CustomerNotFoundException;
import no.kristiania.repositories.customers.Customer;
import no.kristiania.repositories.customers.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerAddressService customerAddressService;
    private final OrderService orderService;
    private final OrderProductService orderProductService;
   @Autowired
    public CustomerService(
            CustomerRepo customerRepo,
            CustomerAddressService customerAddressService,
            OrderService orderService,
            OrderProductService orderProductService
           ) {

       this.customerRepo = customerRepo;
       this.customerAddressService = customerAddressService;
       this.orderService = orderService;
       this.orderProductService = orderProductService;
    }

    // TODO: Fetching a customer should show their addresses and order history.


    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer createCustomer(Customer customer) {
       return customerRepo.save(customer);
    }

    /*
    public Customer getCustomerById(Long id){
       return customerRepo.findById(id).orElse(null);
    }
     */

    public CustomerDTO getCustomerById(Long customerId){
       Customer customer = customerRepo
               .findById(customerId)
               .orElseThrow(() -> new CustomerNotFoundException("Could not find customer by id: " + customerId));
       List<String> customerAddress = customerAddressService.getCustomerAddressByCustomerId(customerId);
       List<OrderDTO> history = orderService.getOrderHistoryByCustomerId(customerId);
       CustomerDTO customerDTO = new CustomerDTO(customer);
       customerDTO.setCustomerAddresses(customerAddress);
       customerDTO.setHistory(history);
       return customerDTO;
    }

    public void deleteCustomer(Long id) {
        if (!customerRepo.existsById(id)) {
            throw new CustomerNotFoundException("Customer not found for ID: " + id);
        }

       customerRepo.deleteById(id);
    }

    public void deleteAllCustomers() {
       customerRepo.deleteAll();
    }

}
