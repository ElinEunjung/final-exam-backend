package no.kristiania.repositories.customers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.kristiania.repositories.orders.Order;

import java.util.List;

@Entity //Entity class is mapping to DB table. This class is JPA entity.
@Data // Automatically generates getters, setters, toString(), equals() and hashCode() method
@NoArgsConstructor // Automatically generates constructors with and without arguments
@AllArgsConstructor
@Table(name = "CUSTOMERS") // Set table name as "customer"
public class Customer {
    @Id // This field is primary key.
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_gen"
    ) // create primary key value automatically
    @SequenceGenerator(
            name="customer_gen",
            sequenceName = "customer_seq",
            allocationSize = 1)
    @Column(
            name = "customer_id",
            nullable = false
    )
    private long id;

    private String name;
    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "customer") // refers to the customer field in the CustomerAddress entity (which is the other side of the relationship).
    @JsonIgnoreProperties("customer")  // TODO: NOT REALLY NECESSARY!! There are no JSON this side of the application
    private List<CustomerAddress> customerAddresses;

    @OneToMany(mappedBy = "customer") // refers to the customer field in the Order entity (which is the other side of the relationship).
    @JsonIgnoreProperties("customer")
    private List<Order> history; // A customer can have multiple orders


//    public Customer(
//            String name,
//            String phoneNumber,
//            String email,
//            List<CustomerAddress> customerAddresses,
//            List<Order> history
//    ) {
//        this.name = name;
//        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.customerAddresses = customerAddresses;
//        this.history = history;
//    }
}
