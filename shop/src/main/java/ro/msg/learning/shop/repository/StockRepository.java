package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.model.StockID;

/*
Stock uses a composite primary key so I use StockID for the ID field.
 */
public interface StockRepository  extends JpaRepository<Stock, StockID> {
}
