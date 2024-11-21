package no.kristiania.repositories.orders;

import jakarta.persistence.*;
import lombok.*;
import no.kristiania.repositories.products.Product;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "ORDER_PRODUCTS")
public class OrderProduct {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_product_gen"
    )
    @SequenceGenerator(
            name = "order_product_gen",
            sequenceName = "order_product_seq",
            allocationSize = 1
    )
    @Column(
            name = "order_product_id",
            nullable = false
    )
    @EqualsAndHashCode.Include
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int productQuantity; // number of products per each order

}
