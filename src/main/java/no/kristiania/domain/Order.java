package no.kristiania.domain;

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
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double shippingCharge;
    private double totalPrice; // product prices + shipping charge
    private boolean isShipped;

    // TODO: Order - Product relationship: One to Many
    // TODO: Order - QuantityOfProduct relationshiqp: Many to Many -> created OrderProduct association entity
        //TODO: Order - OrderProduct(Products) relationship: One to Many
    // TODO: Order - Customer relationship: Many To One
    // TODO: Order - Shipping address relationship: One to One

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> productQuantities; // Holds the list of products and their quantities in the order

    @OneToMany(mappedBy = "order")
    @JoinColumn(name = "order_id") // Foreign key column in the Product table
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "customer_id")  // Foreign key column in the Order table
    private Customer customer;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id") // Foreign key column in the Order table referencing CustomerAddress
    private CustomerAddress shippingAddress;

    public Order(
            double shippingCharge,
            double totalPrice,
            boolean isShipped,
            List<OrderProduct> productQuantities,
            List<Product> products,
            Customer customer,
            CustomerAddress shippingAddress
    ) {
        this.shippingCharge = shippingCharge;
        this.totalPrice = totalPrice;
        this.isShipped = isShipped;
        this.productQuantities = productQuantities;
        this.products = products;
        this.customer = customer;
        this.shippingAddress = shippingAddress;
    }
}
