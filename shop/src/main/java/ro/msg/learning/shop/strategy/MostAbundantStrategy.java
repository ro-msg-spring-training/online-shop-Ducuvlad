package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
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
public class MostAbundantStrategy implements IStrategy {
    @Override
    public List<Stock> getLocationForOrder(Order order, StockRepository stockRepository, OrderDetailRepository orderDetailRepository,LocationRepository locationRepository) throws Exception {
        /*
        Find for each product its shippedFrom location
        query: get location with largest stock quantity  MAX(quantity)


         FOR every OrderDetail with orderDetail.ID==order.ID
            FOR every Location
                FIND Stock with Stock.ProductID==OrderDetail.ProductID AND Stock.quantity>=OrderDetail.quantity AND Stock.LocationID==Location.ID
                    IF quantity>maxquantity
                    THEN setLocationID and maxquantity=quantity
        */
        /*orderDetailRepository.findOrderDetailsByOrderID(order.getId()).stream()
                .map(od -> {
                    return
                    stockRepository.findOrderDetailsByOrderID(od.getOrderDetailID().getProductID(),od.getQuantity());
                }).max(s->Comparator.comparing(s.))*/
        List<Stock> productStock=new ArrayList<>();
        for(OrderDetail detail: orderDetailRepository.findOrderDetailsByOrderID(order.getId())){
            //get the largest stock for product
            Optional <Stock> largestStock= stockRepository.findLargestStockForProduct(detail.getOrderDetailID().getProductID(),detail.getQuantity());
            if(largestStock.isPresent())
                productStock.add(largestStock.get());
            else
                throw new Exception("No suitable location was found");//todo make exception class
        }
        return productStock;
    }
}