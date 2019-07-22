package ro.msg.learning.shop.controller;

import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.OrderAndDetailsDTO;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.service.OrderAndDetailService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderAndDetailService orderService;

    public OrderController(OrderAndDetailService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public List<OrderDTO> createProduct(@RequestBody OrderAndDetailsDTO order) {
        List<Order> ordersToSave=orderService.saveOrders(order);
        return ordersToSave.stream().map(o-> new OrderDTO(o.getCreatedAt(),o.getCountry(),o.getCity(),o.getCounty(),
                o.getStreetAddress(),o.getShippedFrom(),o.getCustomer())).collect(Collectors.toList());
    }
}
