package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.Location;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO implements Serializable {
    private Date createdAt;
    private String country;
    private String city;
    private String county;
    private String streetAddress;
    private Location shippedFrom;
    private Customer customerID;
}
