package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends BaseRepository<Customer, Integer> {
    Optional<Customer> findFirstByUsername(String username);
}
