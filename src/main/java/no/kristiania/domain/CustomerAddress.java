package no.kristiania.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_address")

public class CustomerAddress {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;
    private String address;

//    @OneToOne(mappedBy = "shippingAddress") // Indicates this side is the inverse of the relationship
//    private Order order; // Optional if we need a bidirectional relationship

    @ManyToOne
    @JoinColumn(name = "customer_address_id")
    private Customer customer;

    public CustomerAddress(
            String address,
            Customer customer
    ) {
        this.address = address;
        this.customer = customer;
    }

}
