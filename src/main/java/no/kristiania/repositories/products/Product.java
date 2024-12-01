package no.kristiania.repositories.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.kristiania.models.ProductStatus;
import no.kristiania.repositories.orders.OrderProduct;

import java.util.List;

@Entity //Entity class is mapping to DB table. This class is JPA entity.
@Data // Automatically generates getters, setters, toString(), equals() and hashCode() method
@NoArgsConstructor // Automatically generates constructors with and without arguments
//@AllArgsConstructor
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_gen"
    )
    @SequenceGenerator(
            name = "product_gen",
            sequenceName = "product_seq",
            allocationSize = 1
    )
    @Column(
            name = "product_id",
            nullable = false
    )
    private Long productId;

    private String name;
    private String description;
    private float price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(name = "quantity_in_stock")
    private int quantityInStock; // same with "quantityOnHand"

    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties("product")
    private List<OrderProduct> orderProducts;

    public Product(
            String name,
            String description,
            float price,
            ProductStatus status,
            int quantityInStock
    ) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.quantityInStock = quantityInStock;
    }
}
