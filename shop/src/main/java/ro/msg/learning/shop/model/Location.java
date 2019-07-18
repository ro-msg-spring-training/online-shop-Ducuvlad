package ro.msg.learning.shop.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;/*

 */
@AllArgsConstructor
@Entity
@Table(name = "Location_tbl")
@Data
@ToString()
public class Location extends BaseEntity<Integer> {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "addresscountry", nullable = false)
    private String country;
    @Column(name = "addresscity", nullable = false)
    private String city;
    @Column(name = "addresscounty", nullable = false)
    private String county;
    @Column(name = "addressstreetaddress", nullable = false)
    private String streetAddress;

}
