package no.kristiania.services;

import no.kristiania.repositories.orders.Order;
import no.kristiania.repositories.orders.OrderProduct;
import no.kristiania.repositories.orders.OrderProductRepo;
import no.kristiania.repositories.orders.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //@Service represents this class is service layer.
public class OrderProductService {

    private final OrderProductRepo orderProductRepo;

    @Autowired
    public OrderProductService(OrderProductRepo orderProductRepo) {
        this.orderProductRepo = orderProductRepo;
    }


    //Read
    public List<OrderProduct> getAllOrderProducts() {
        return orderProductRepo.findAll();
    }

    //Create
    public OrderProduct createOrderProduct(OrderProduct orderProduct) {
        return orderProductRepo.save(orderProduct);
    }

    //Update
    public OrderProduct getOrderProductById(Long id) {
        return orderProductRepo.findById(id).orElse(null);
    }

    //Delete
    public void deleteOrderProduct(Long id) {
        orderProductRepo.deleteById(id);
    }

    public void deleteAllOrderProducts() {
        orderProductRepo.deleteAll();
    }
}


