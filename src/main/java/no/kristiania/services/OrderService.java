package no.kristiania.services;

import no.kristiania.repositories.customers.Customer;
import no.kristiania.repositories.orders.Order;
import no.kristiania.repositories.orders.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //@Service represents this class is service layer.
public class OrderService {

    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }


    //Read
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    //Create
    public Order createOrder(Order order) {
        return orderRepo.save(order);
    }

    //Update
    public Order getOrderById(Long id) {
        return orderRepo.findById(id).orElse(null);
    }

    //Delete
    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }

}


