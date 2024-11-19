package no.kristiania.repository;

import no.kristiania.domain.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepo extends JpaRepository<CustomerAddress,Long> {
}
