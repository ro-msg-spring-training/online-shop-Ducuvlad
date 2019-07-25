package ro.msg.learning.shop.strategy;
import ro.msg.learning.shop.dto.OrderAndDetailsDTO;
import ro.msg.learning.shop.model.Location;

import java.util.List;

public interface IStrategy {
    List<Location> getLocationsForOrder(OrderAndDetailsDTO order) ;
}

