package no.kristiania.repositories.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.kristiania.repositories.customers.Customer;

import java.util.List;

@Entity //Entity class is mapping to DB table. This class is JPA entity.
@Data // Automatically generates getters, setters, toString(), equals() and hashCode() method
@NoArgsConstructor // Automatically generates constructors with and without arguments
@AllArgsConstructor
@Table(name = "ORDERS") // !! // Changed table name to avoid reserved keyword conflict
public class Order {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_gen"
    )
    @SequenceGenerator(
            name = "order_gen",
            sequenceName = "order_seq",
            allocationSize = 1
    )
    @Column(
            name = "order_id",
            nullable = false
    )
    private Long id;
    private String shippingAddress;
    private float shippingCharge;
    private float totalPrice; // product prices + shipping charge
    private boolean isShipped;


    @OneToMany(mappedBy = "order")
    @JsonIgnoreProperties("order")
    private List<OrderProduct> productQuantities; // Holds the list of products and their quantities in the order

    @ManyToOne
    @JoinColumn(name = "customer_id")  // Refers to Customer's primary key
    private Customer customer;


    public Order(
            String shippingAddress,
            float shippingCharge,
            float totalPrice,
            boolean isShipped,
            List<OrderProduct> productQuantities,
            Customer customer

    ) {
        this.shippingAddress = shippingAddress;
        this.shippingCharge = shippingCharge;
        this.totalPrice = totalPrice;
        this.isShipped = isShipped;
        this.productQuantities = productQuantities;
        this.customer = customer;
    }
}
