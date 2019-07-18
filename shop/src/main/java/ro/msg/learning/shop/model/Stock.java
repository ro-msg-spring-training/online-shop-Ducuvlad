package ro.msg.learning.shop.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
/*

 */
@AllArgsConstructor
@Entity
@Table(name = "stock_tbl")
@Data
@ToString
public class Stock implements java.io.Serializable{

    @EmbeddedId
    private StockID stockID;
   /* @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "pid")
    private Product product ;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "lid")
    private Location location;*/
    @Column(name = "quantity", nullable = false)
    private int quantity;
}
