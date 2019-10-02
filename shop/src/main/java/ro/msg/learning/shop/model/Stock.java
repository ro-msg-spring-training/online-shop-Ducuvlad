package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
/*

 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock_tbl")
@Data
@ToString
public class Stock implements java.io.Serializable{

    @EmbeddedId
    private StockID stockID;
    @Column(name = "quantity", nullable = false)
    private int quantity;
}
