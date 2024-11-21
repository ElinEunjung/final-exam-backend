package no.kristiania.repositories.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//JpaRepository offers basic CRUD methods, paging & array
@Repository
public interface OrderProductRepo extends JpaRepository<OrderProduct,Long> {
}
