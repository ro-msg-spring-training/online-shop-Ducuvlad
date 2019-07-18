package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.OrderDetailID;
/*

 */
public interface OrderDetailRepository  extends JpaRepository<OrderDetail, OrderDetailID> {
}
