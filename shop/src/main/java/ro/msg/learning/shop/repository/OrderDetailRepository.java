package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.OrderDetailID;

import java.util.Collection;

/*

 */
public interface OrderDetailRepository  extends JpaRepository<OrderDetail, OrderDetailID> {
    @Query("SELECT od FROM OrderDetail od where od.orderDetailID.oid = orderID")
    Collection<OrderDetail> findOrderDetailsByOrderID(@Param("orderID") int id);

    @Query("SELECT od FROM OrderDetail od where od.orderDetailID.pid = purchaseID")
    OrderDetail findOrderDetailsByProductID(@Param("purchaseID") int purchaseID);
}
