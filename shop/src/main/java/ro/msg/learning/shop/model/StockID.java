package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;/*

 */
@AllArgsConstructor
@Embeddable
@Data
public class StockID implements Serializable {
    @Column(name = "pid")
    private int purchaseID;

    @Column(name = "lid")
    private int locationID;
}
