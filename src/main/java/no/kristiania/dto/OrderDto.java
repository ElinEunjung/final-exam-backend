package no.kristiania.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import no.kristiania.repositories.customers.Customer;
import no.kristiania.repositories.orders.Order;
import no.kristiania.repositories.products.Product;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    private Customer customer;
    private String shippingAddress;
    private Order isShipped;
    private Product quantityInStock;
}
