package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.exception.NoOrderDetailException;
import ro.msg.learning.shop.exception.NoProductException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RevenueService {
    private RevenueRepository revenueRepository;
    private LocationRepository locationRepository;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private ProductRepository productRepository;

    //todo make scheduled task
    @Scheduled(cron = "0 0 0 * * *")
    public void saveRevenueDaily() {
        Date date = Date.valueOf(LocalDate.now().minusDays(1));//get yesterday
        saveRevenues(date);
    }

    public List<Revenue> getRevenueFromDate(Date date) {
        List<Revenue> revenues = revenueRepository.getAllByDate(date);
        if (revenues.isEmpty())
            return saveRevenues(date);
        else return revenues;
    }

    public List<Revenue> saveRevenues(Date date) {
        List<Revenue> revenues = locationRepository.findAll().stream()
                .map(l -> generateRevenue(date, l))
                .collect(Collectors.toList());
        revenues.forEach(revenueRepository::save);
        return revenues;
    }

    public Revenue generateRevenue(Date date, Location location) {
        //calculate revenue for date ==
        //get orders that have date and location
        //for each order calculate order revenue and sum them up
        //create new Revenue(sum,date,location)
        List<Order> orders = orderRepository.getAllByCreatedAtAndShippedFrom(date, location);

        BigDecimal sum = orders.stream()
                .map(this::calculateOrderRevenue).reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Revenue(date, sum, location);
    }

    private BigDecimal calculateOrderRevenue(Order order) {
        OrderDetail orderDetail = orderDetailRepository.findByOrderDetailID_OrderID(order.getId()).orElseThrow(() -> new NoOrderDetailException("No order detail found"));//todo throw custom ex
        Product product = productRepository.findById(orderDetail.getOrderDetailID().getProductID()).orElseThrow(() -> new NoProductException("No product found"));
        Double productPrice = product.getPrice().doubleValue();
        Integer quantity = orderDetail.getQuantity();
        return new BigDecimal(productPrice * quantity);

    }
}
