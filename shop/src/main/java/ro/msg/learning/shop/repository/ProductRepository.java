package ro.msg.learning.shop.repository;

import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.model.Product;

/*

 */
@Repository
public interface ProductRepository extends BaseRepository<Product, Integer> {
}