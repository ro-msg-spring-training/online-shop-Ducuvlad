package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.OrderDetail;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderAndDetailsDTO implements Serializable {
    private Date createdAt;
    private String country;
    private String city;
    private String county;
    private String streetAddress;
    private int shippedFromID;
    private List<OrderDetail> details;

    public OrderAndDetailsDTO(Order order, List<OrderDetail> orderDetail) {
        this.city = order.getCity();
        this.country = order.getCountry();
        this.county = order.getCounty();
        this.streetAddress =order.getStreetAddress();
        this.createdAt=order.getCreatedAt();
        this.details=orderDetail;
        this.shippedFromID=order.getShippedFrom().getId();
    }
}
