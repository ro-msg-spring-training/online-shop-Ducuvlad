package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;/*

 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orderdetail_tbl")
@Data
@ToString
public class OrderDetail implements java.io.Serializable{
    @EmbeddedId
    private OrderDetailID orderDetailID;
    @Column(name = "quantity", nullable = false)
    private int quantity;
}