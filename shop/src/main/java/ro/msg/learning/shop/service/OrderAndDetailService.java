package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.OrderAndDetailsDTO;
import ro.msg.learning.shop.dto.ProductQuantityDTO;
import ro.msg.learning.shop.exception.NoLocationException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.strategy.IStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class OrderAndDetailService {
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private StockRepository stockRepository;
    private CustomerRepository customerRepository;
    private final IStrategy strategy;


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

    public List<Order> saveOrders (OrderAndDetailsDTO requirements){
        //create orders for each different location
        /*AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.refresh();
        IStrategy strategy=ctx.getBean(IStrategy.class);*/
        List<Order> newOrders=new ArrayList<>();
        try {
            //get list of stocks used by the order
            List<Location> productLocations=strategy.getLocationsForOrder(requirements);
            List<ProductQuantityDTO> products=requirements.getProducts();
            //modify stock quantity
            for(int i=0;i<productLocations.size();i++) {
                Optional<Stock> stock = stockRepository.findStockDetailByPK(products.get(i).getProductID(), productLocations.get(i).getId());

                Stock s;
                if (stock.isPresent()) {
                    s = stock.get();
                    s.setQuantity(s.getQuantity() - requirements.getProducts().get(i).getQuantity());
                }
            }
            //save orders
            productLocations.stream().distinct().map(location->new Order(requirements.getCreatedAt(),
                    requirements.getCountry(),
                    requirements.getCity(),
                    requirements.getCounty(),
                    requirements.getStreetAddress(),
                    location,
                    customerRepository.getOne(requirements.getCustomerID())))
                    .forEach(order ->
                    {newOrders.add(order);
                    orderRepository.save(order);
                    } );
        } catch (NoLocationException e) {
            e.printStackTrace();
        }
        return newOrders;
    }
}