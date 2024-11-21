package no.kristiania.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//JpaRepository offers basic CRUD methods, paging & array
@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
}
