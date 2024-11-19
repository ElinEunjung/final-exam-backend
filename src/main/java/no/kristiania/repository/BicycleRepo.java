package no.kristiania.repository;

import no.kristiania.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
//JpaRepository offers basic CRUD methods, paging & array
public interface BicycleRepo extends JpaRepository<Customer, Long> {
}
