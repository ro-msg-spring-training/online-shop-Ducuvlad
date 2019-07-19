package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;

public interface IStrategy {

    public List<Stock> getLocationForOrder(Order order, StockRepository stockRepository, OrderDetailRepository orderDetailRepository, LocationRepository locationRepository) throws Exception;
}

