package ro.msg.learning.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.Model.OrderDetail;
import ro.msg.learning.shop.Model.OrderDetailID;

public interface OrderDetailRepository  extends JpaRepository<OrderDetail, OrderDetailID> {
}
