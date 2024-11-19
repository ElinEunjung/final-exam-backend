package no.kristiania.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity //Entity class is mapping to DB table. This class is JPA entity.
@Data // Automatically generates getters, setters, toString(), equals() and hashCode() method
@NoArgsConstructor // Automatically generates constructors with and without arguments

public class Customer {
    @Id // This field is primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // create primary key value automatically
    private String name;
    private String phoneNumber;
    private String email;

    // TODO: Customer - History relationship: One to one
    // TODO: History - Order relationship: One to many

    @OneToMany
    @JoinColumn(name = "customer_address_id")
    private List<CustomerAddress> customerAddresses;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<Order> history;


    public Customer(
            String name,
            String phoneNumber,
            String email,
            List<CustomerAddress> customerAddresses,
            List<Order> history
    ) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.customerAddresses = customerAddresses;
        this.history = history;
    }
}
