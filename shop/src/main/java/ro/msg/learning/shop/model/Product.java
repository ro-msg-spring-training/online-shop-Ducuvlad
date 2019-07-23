package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
/*

 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "Product_tbl")
public class Product extends BaseEntity<Integer> {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "weight", nullable = false)
    private double weight;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "pcid")
    private ProductCategory productCategory;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "sid")
    private Supplier supplier;
    @Column(name = "imageurl", nullable = false)
    private String imageUrl;

    @Override
    public String toString() {
        return "Base{" +
                "Product =" + name
                + '\'' +",Description="+ description +
                + '\'' +",Price="+ price +
                + '\'' +",Weight="+ weight +
                + '\'' +",ProductCategory="+ productCategory.getName() +
                + '\'' +",Supplier="+ supplier.getName() +
                + '\'' +",URL="+ imageUrl +
                super.toString();
    }
}

