package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class SingleLocationStrategy implements IStrategy {


    @Override
    public List<Stock> getLocationForOrder(Order order, StockRepository stockRepository, OrderDetailRepository orderDetailRepository, LocationRepository locationRepository) throws Exception  {
        /*
         FOR every OrderDetail with orderDetail.ID==order.ID
            FOR every Location
                FOR every Stock with Stock.ProductID==OrderDetail.ProductID AND Stock.quantity>=OrderDetail.quantity) AND Stock.LocationID==Location.ID

         query: get Location.lid that has all stocks needed by orderDetail with necessary quantity

        */
        Boolean foundStock;
        List<Stock> productStock=new ArrayList<>();
        Optional<Stock> locationID;
        for(Location location:locationRepository.findAll()) {
            foundStock = true;
            for (OrderDetail orderDetail : orderDetailRepository.findOrderDetailsByOrderID(order.getId())) {
                        locationID = stockRepository.findStock(orderDetail.getOrderDetailID().getProductID(),
                        orderDetail.getQuantity(),
                        location.getId());
                if(!locationID.isPresent()){
                    foundStock=false;
                }
                if (foundStock) {
                    productStock.add(locationID.get());
                    return productStock;
                }
            }
        }
        throw new Exception("NO LOCATION THAT CONTAINS ALL PRODUCTS FOUND");
    }
}
