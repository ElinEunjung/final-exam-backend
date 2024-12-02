package no.kristiania.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import no.kristiania.repositories.customers.Customer;
import no.kristiania.repositories.customers.CustomerAddress;
import no.kristiania.repositories.orders.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {
    public CustomerDTO(
            Customer customer
    ) {
        name = customer.getName();
        phoneNumber = customer.getPhoneNumber();
        email = customer.getEmail();
    }

    private String name;
    private String phoneNumber;
    private String email;

    private List<String> customerAddresses;
    private List<OrderDTO> history;           // A customer can have multiple orders



}
