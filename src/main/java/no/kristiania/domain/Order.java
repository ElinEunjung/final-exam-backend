package no.kristiania.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Entity //Entity class is mapping to DB table. This class is JPA entity.
@Data // Automatically generates getters, setters, toString(), equals() and hashCode() method
@NoArgsConstructor // Automatically generates constructors with and without arguments
@AllArgsConstructor
@Table(name = "orders") // !! // Changed table name to avoid reserved keyword conflict
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_gen")
    @SequenceGenerator(name = "order_gen", sequenceName = "order_seq")
    @Column(name = "order_id", nullable = false)
    private Long id;

    private double shippingCharge;
    private double totalPrice; // product prices + shipping charge
    private boolean isShipped;


    @OneToMany(mappedBy = "order")
    @JsonIgnoreProperties("order")
    private List<OrderProduct> productQuantities; // Holds the list of products and their quantities in the order

//    @OneToMany(mappedBy = "order")
//    @JsonIgnoreProperties("order")
//    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "customer_id")  // Refers to Customer's primary key
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id") // Refers to CustomerAddress's primary key
    private CustomerAddress shippingAddress;

    public Order(
            double shippingCharge,
            double totalPrice,
            boolean isShipped,
            List<OrderProduct> productQuantities,
//            List<Product> products,
            Customer customer,
            CustomerAddress shippingAddress
    ) {
        this.shippingCharge = shippingCharge;
        this.totalPrice = totalPrice;
        this.isShipped = isShipped;
        this.productQuantities = productQuantities;
//        this.products = products;
        this.customer = customer;
        this.shippingAddress = shippingAddress;
    }
}
