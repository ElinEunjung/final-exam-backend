package no.kristiania.repositories.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//JpaRepository offers basic CRUD methods, paging & array
@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
}
