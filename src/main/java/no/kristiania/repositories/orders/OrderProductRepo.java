package no.kristiania.repositories.orders;

import no.kristiania.repositories.customers.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//JpaRepository offers basic CRUD methods, paging & array
@Repository
public interface OrderProductRepo extends JpaRepository<OrderProduct,Long> {

    @Query("SELECT orderProduct FROM OrderProduct orderProduct WHERE orderProduct.order.orderId = :order_id")
    public Optional<OrderProduct> findOrderProductByOrderId(Long order_id);

}
