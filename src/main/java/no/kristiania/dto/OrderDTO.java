package no.kristiania.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import no.kristiania.repositories.customers.Customer;
import no.kristiania.repositories.orders.Order;
import no.kristiania.repositories.orders.OrderProduct;
import no.kristiania.repositories.products.Product;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private String candyName;
    private int productQuantity;
    private String shippingAddress;
    private float shippingCharge;
    private float totalPrice;
    private boolean isShipped;

    public OrderDTO(
            OrderProduct orderProduct
    ) {
        Product product = orderProduct.getProduct();
        Order order = orderProduct.getOrder();
        candyName = product.getCandyName();
        productQuantity = orderProduct.getProductQuantity();
        shippingAddress = order.getShippingAddress();
        shippingCharge = order.getShippingCharge();
        totalPrice = order.getTotalPrice();
        isShipped = order.isShipped();
    }


}
