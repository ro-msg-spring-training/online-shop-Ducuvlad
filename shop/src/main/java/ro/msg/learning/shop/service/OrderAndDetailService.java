package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.strategy.IStrategy;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class OrderAndDetailService {
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private StockRepository stockRepository;
    private LocationRepository locationRepository;
    //todo you need to give repos to IStrategy



    public List<OrderDetail> findAll() {
        return  orderDetailRepository.findAll();
    }

    public Order findByID(int ID) {
        Optional<Order> Order = orderRepository.findById(ID);
        return Order.orElse(null);

    }


    public Order saveOrder(Order Order) {
        return orderRepository.save(Order);
    }

    public int findLocation(Order order){
        /*
        EXECUTE STRATEGY
       */
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        IStrategy strategy=ctx.getBean(IStrategy.class);
        try {
            List<Stock> productStock=strategy.getLocationForOrder(order,stockRepository,orderDetailRepository,locationRepository);
            productStock.stream().forEach(s->s.setQuantity(s.getQuantity()-orderDetailRepository.findOrderDetailsByProductID(s.getStockID().getPurchaseID())));
            //for each stock that has lid=location sent from strategy , stock.quantity-orderDetail.Quantity where orderDetail.pid=stock.pid
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}