package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Order;


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
    private List<ProductQuantityDTO> products;
    private int customerID;

    public OrderAndDetailsDTO(Order order, List<ProductQuantityDTO> productsDetails) {
        this.city = order.getCity();
        this.country = order.getCountry();
        this.county = order.getCounty();
        this.streetAddress =order.getStreetAddress();
        this.createdAt=order.getCreatedAt();
        this.products=productsDetails;
        this.customerID=order.getCustomer().getId();
    }
}
