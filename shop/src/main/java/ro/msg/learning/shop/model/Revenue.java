package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/*

 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@ToString
@Table(name = "Revenue_tbl")
public class Revenue extends BaseEntity<Integer> {
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "sum", nullable = false)
    private BigDecimal sum;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "lid")
    private Location location;

}