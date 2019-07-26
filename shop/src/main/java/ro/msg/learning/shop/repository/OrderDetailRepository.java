package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.OrderDetailID;


/*

 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailID> {
    /*@Query("SELECT od FROM OrderDetail od where od.orderDetailID.oid = orderID")
    Collection<OrderDetail> findOrderDetailsByOrderID(@Param("orderID") int id);

    @Query("SELECT od FROM OrderDetail od where od.orderDetailID.pid = productID")
    OrderDetail findOrderDetailsByProductID(@Param("productID") int productID);*/
}
