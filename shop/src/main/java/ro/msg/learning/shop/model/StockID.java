package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;/*

 */
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Data
public class StockID implements Serializable {
    @Column(name = "pid")
    private int productID;

    @Column(name = "lid")
    private int locationID;
}
