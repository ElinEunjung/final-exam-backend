package no.kristiania.services;

import no.kristiania.errors.CustomerNotFoundException;
import no.kristiania.errors.OrderNotFoundException;
import no.kristiania.errors.ProductNotFoundException;
import no.kristiania.repositories.customers.Customer;
import no.kristiania.repositories.orders.Order;
import no.kristiania.repositories.orders.OrderDto;
import no.kristiania.repositories.orders.OrderProduct;
import no.kristiania.repositories.orders.OrderRepo;
import no.kristiania.repositories.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //@Service represents this class is service layer.
public class OrderService {

    private final OrderRepo orderRepo;
    private final CustomerService customerService;
    private final ProductService productService;


    @Autowired
    public OrderService(
            OrderRepo orderRepo,
            CustomerService customerService,
            ProductService productService
    ) {

        this.orderRepo = orderRepo;
        this.customerService = customerService;
        this.productService = productService;
    }


    //Read
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
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
    public void validateAndUpdateOrder(OrderDto orderDto) {
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

    // TODO: Fetching an order should show the customer and shipping address.

    // Order tracking
    public OrderDto fetchOrderById(Long orderId) {
        Order order = orderRepo
                .findById(orderId)
                .orElseThrow(() ->
               new  OrderNotFoundException("Order not found for ID: " + orderId));

        OrderDto orderDto = new OrderDto();
        orderDto.setCustomer(order.getCustomer());
        orderDto.setShippingAddress(order.getShippingAddress());

        return orderDto;
    }


    //Delete
    public void deleteOrder(Long id) {
        if (!orderRepo.existsById(id)) {
            throw new OrderNotFoundException("Order not found for ID: " + id);
        }
        orderRepo.deleteById(id);
    }

    public void deleteAllOrders() {
        orderRepo.deleteAll();
    }

}


