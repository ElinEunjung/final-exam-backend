package no.kristiania.repositories.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//JpaRepository offers basic CRUD methods, paging & array
@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o WHERE o.customer.customerId = :cust_id")
    List<Order> getOrderByCustomerId(Long cust_id);
}
