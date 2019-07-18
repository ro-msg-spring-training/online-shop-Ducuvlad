package ro.msg.learning.shop.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.Table;/*

         */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Productcategory_tbl")
@Data
public class ProductCategory extends BaseEntity<Integer> {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;

    @Override
    public String toString() {
        return "Base{" +
                " Category=" + name
                + '\'' +",Description="+ description +
                super.toString();
    }
}
