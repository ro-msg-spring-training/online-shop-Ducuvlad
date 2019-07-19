package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.model.StockID;

import java.util.Collection;
import java.util.Optional;

/*
Stock uses a composite primary key so I use StockID for the ID field.
 */
public interface StockRepository  extends JpaRepository<Stock, StockID> {
    @Query("SELECT s FROM Stock s where s.stockID.pid = pid and s.stockID.lid = lid")
    Optional<Stock> findStockDetailByPK(@Param("ProductID") int pid,@Param("LocationID") int lid);
    //if exists stock.oid==orderDetail.oid and stock.lid==location.ID and stock.quantity>orderDetail.quantity
    @Query("SELECT s FROM Stock s where s.stockID.pid = pid and s.stockID.lid =lid  and s.quantity>=detailQuantity")
    Optional<Stock> findStock(@Param("productID") int pid, @Param("detailQuantity") int detailQuantity, @Param("LocationID") int lid);

    @Query("SELECT s FROM Stock s where s.stockID.pid = pid  and s.quantity=max(s.quantity) and s.quantity>=detailQuantity")
    Optional<Stock> findLargestStockForProduct(@Param("productID") int pid, @Param("detailQuantity") int detailQuantity);
}
