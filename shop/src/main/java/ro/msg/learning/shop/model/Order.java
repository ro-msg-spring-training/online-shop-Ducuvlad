package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;/*

 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@ToString
@Table(name = "order_tbl")
public class Order extends BaseEntity<Integer> {
    @Column(name = "createdat", nullable = false)
    private Date createdAt;
    @Column(name = "addresscountry", nullable = false)
    private String country;
    @Column(name = "addresscity", nullable = false)
    private String city;
    @Column(name = "addresscounty", nullable = false)
    private String county;
    @Column(name = "addressstreetaddress", nullable = false)
    private String streetAddress;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "lid")
    private Location shippedFrom;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "cid")
    private Customer customer;

}

