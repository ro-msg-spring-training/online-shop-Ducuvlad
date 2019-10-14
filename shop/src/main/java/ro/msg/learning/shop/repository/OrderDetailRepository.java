package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.OrderDetailID;

import java.util.Optional;


public interface OrderDetailRepository  extends JpaRepository<OrderDetail, OrderDetailID> {

    Optional<OrderDetail> findByOrderDetailID_OrderID(Integer orderID);

    /*@Query("SELECT od FROM OrderDetail od where od.orderDetailID.pid = productID")
    OrderDetail findOrderDetailsByProductID(@Param("productID") int productID);*/
}
