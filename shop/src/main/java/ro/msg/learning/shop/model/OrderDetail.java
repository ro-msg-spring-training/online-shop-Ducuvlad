package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;/*

 */
@AllArgsConstructor
@Entity
@Table(name = "orderdetail_tbl")
@Data
@ToString
public class OrderDetail implements java.io.Serializable{
    @EmbeddedId
    private OrderDetailID orderDetailID;
    /*@ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "oid")
    private Order order;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "pid")
    private Product product;*/
    @Column(name = "quantity", nullable = false)
    private int quantity;
}