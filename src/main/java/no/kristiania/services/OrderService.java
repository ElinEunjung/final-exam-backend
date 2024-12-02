package no.kristiania.services;

import no.kristiania.exceptions.OrderNotFoundException;
import no.kristiania.exceptions.ProductNotFoundException;
import no.kristiania.repositories.orders.Order;
import no.kristiania.dto.OrderDTO;
import no.kristiania.repositories.orders.OrderProduct;
import no.kristiania.repositories.orders.OrderRepo;
import no.kristiania.repositories.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final ProductService productService;

    private final OrderProductService orderProductService;


    @Autowired
    public OrderService(
            OrderRepo orderRepo,
            ProductService productService,
            OrderProductService orderProductService
    ) {

        this.orderRepo = orderRepo;
        this.productService = productService;
        this.orderProductService = orderProductService;
    }


    //Read
    public List<OrderDTO> getAllOrders() {
        return orderRepo
                .findAll()
                .stream()
                .map(this::enrichOrderWithProductInformation)
                .collect(Collectors.toList());
    }

    //Create
    public Order createOrder(Order order) {
        return orderRepo.save(order);
    }

    //update
//    public Order updateOrder(OrderDto orderDto) {
//        // check customer
//        Customer customer = customerService.getCustomerById(orderDto.getCustomer().getId());
//        if (customer == null) {
//            throw new CustomerNotFoundException("Customer not found for ID: " + orderDto.getCustomer().getId());
//        }
//
//        Order order = new Order();
//        order.setCustomer(customer);
//        order.setShippingAddress(orderDto.getShippingAddress());
//
//        validateAndUpdateOrder(orderDto);
//
//        return orderRepo.save(order);
//    }

    // ToDo: Throw ProductNotFoundException ,when productStatus is OUT_OF_STOCK or DISCONTINUED.


    // TODO: Placing an order should update the status and quantity on hand of a product,
    //  and the system should not allow products to be ordered that are out of stock.
    public void validateAndUpdateOrder(OrderDTO orderDto) {
        Order order = new Order();
        for (OrderProduct orderProduct: order.getProductQuantities()) {
            Product product = productService.getProductById(orderProduct.getOrderProductId());
            if(product.getQuantityInStock() < orderProduct.getProductQuantity()) {
                throw new ProductNotFoundException("Product out of stock: " + product.getProductId());
            }
            product.setQuantityInStock(product.getQuantityInStock() - orderProduct.getProductQuantity());
            productService.updateProduct(product);
        }

        orderRepo.save(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepo.findById(orderId).orElse(null);
    }

    public List<OrderDTO> getOrderHistoryByCustomerId(Long customerId) {
        return orderRepo.getOrderByCustomerId(customerId)
                .stream()
                .map((this::enrichOrderWithProductInformation))
                .collect(Collectors.toList());
    }

    // TODO: Fetching an order should show the customer and shipping address.

    // Order tracking
    public OrderDTO fetchOrderById(Long orderId) {
        Order order = orderRepo
                .findById(orderId)
                .orElseThrow(() ->
               new  OrderNotFoundException("Order not found for ID: " + orderId));

        return enrichOrderWithProductInformation(order);
    }


    //Delete
    public void deleteOrder(Long id) {
        if (!orderRepo.existsById(id)) {
            throw new OrderNotFoundException("Order not found for ID: " + id);
        }
        orderRepo.deleteById(id);
    }

    private OrderDTO enrichOrderWithProductInformation(Order order) {
        Long orderId = order.getOrderId();
        OrderProduct orderProduct = orderProductService.getOrderProductByOrderId(orderId);
        return new OrderDTO(orderProduct);
    }
}
