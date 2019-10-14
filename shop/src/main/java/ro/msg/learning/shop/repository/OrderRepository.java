package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Order;

import java.util.Date;
import java.util.List;

/*

 */
public interface OrderRepository extends BaseRepository<Order, Integer> {
    List<Order> getAllByCreatedAtAndShippedFrom(Date date, Location location);
}
