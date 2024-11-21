package no.kristiania.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "order_product")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_product_gen")
    @SequenceGenerator(name = "order_product_gen", sequenceName = "order_product_seq")
    @Column(name = "order_product_id", nullable = false)
    @EqualsAndHashCode.Include
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int productQuantity; // Store the quantity for each product in the order



}
