package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.msg.learning.shop.model.Customer;

import java.util.Optional;

/*

 */
public interface CustomerRepository extends BaseRepository<Customer, Integer> {
    @Query("SELECT c FROM Customer c where c.username=:username")
    Optional<Customer> findCustomerByUsername(@Param("username") String username);
}
