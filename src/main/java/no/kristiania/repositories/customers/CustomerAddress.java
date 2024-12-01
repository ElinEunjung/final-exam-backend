package no.kristiania.repositories.customers;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMER_ADDRESSES")

public class CustomerAddress {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_address_gen"
    )
    @SequenceGenerator(
            name="customer_address_gen",
            sequenceName = "customer_address_seq",
            allocationSize = 1
    )
    @Column(
            name = "customer_address_id",
            nullable = false
    )
    private Long id;

    private String address;

    @ManyToOne
    @JoinColumn(name = "customer_id") // Refers to Customer's primary key
    private Customer customer;

    public CustomerAddress(
            String address,
            Customer customer
    ) {
        this.address = address;
        this.customer = customer;
    }

}
