package no.kristiania.controller;


import no.kristiania.repositories.orders.Order;
import no.kristiania.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //REST API Controller. All method response with JSON form
@RequestMapping("/api/order") //Handle HTTP requests with the corresponding path
public class OrderController {
    private final OrderService orderService;

    @Autowired //Constructor Injection
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping //Mapping Read method
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> order = orderService.getAllOrders();
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping //Mapping Create method
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}") //Mapping Read Method
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{id}") //Mapping Delete method
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>("Your order is deleted", HttpStatus.OK);
    }

   }
