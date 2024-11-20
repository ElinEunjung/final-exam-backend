package no.kristiania.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.kristiania.models.ProductStatus;

@Entity //Entity class is mapping to DB table. This class is JPA entity.
@Data // Automatically generates getters, setters, toString(), equals() and hashCode() method
@NoArgsConstructor // Automatically generates constructors with and without arguments
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int price;
    private ProductStatus status;
    private int quantityOnHand;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Product(
            Long id,
            String name,
            String description,
            int price,
            ProductStatus status,
            int quantityOnHand
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.quantityOnHand = quantityOnHand;
    }
}
