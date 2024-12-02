package no.kristiania.repositories.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//JpaRepository offers basic CRUD methods, paging & array
@Repository
public interface CustomerAddressRepo extends JpaRepository<CustomerAddress,Long> {
    @Query("SELECT address FROM CustomerAddress address WHERE address.customer.customerId = :cust_id")
    public List<CustomerAddress> findCustomerAddressByCustomerId(Long cust_id);
}
