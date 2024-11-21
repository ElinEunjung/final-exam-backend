package no.kristiania.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.kristiania.models.ProductStatus;

import java.util.List;

@Entity //Entity class is mapping to DB table. This class is JPA entity.
@Data // Automatically generates getters, setters, toString(), equals() and hashCode() method
@NoArgsConstructor // Automatically generates constructors with and without arguments
//@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_gen")
    @SequenceGenerator(name = "product_gen", sequenceName = "product_seq")
    @Column(name = "product_id", nullable = false)
    private Long id;

    private String name;
    private String description;
    private int price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private int quantityOnHand;

    @ManyToOne
    @JoinColumn(name = "order_product_id")
    private OrderProduct products; // ??

    public Product(
            Long id,
            String name,
            String description,
            int price,
            ProductStatus status,
            int quantityOnHand,
            OrderProduct products
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.quantityOnHand = quantityOnHand;
        this.products = products;
    }
}
