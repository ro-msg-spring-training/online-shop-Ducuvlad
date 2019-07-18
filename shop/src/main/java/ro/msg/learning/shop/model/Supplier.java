package ro.msg.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/*

 */
@AllArgsConstructor
@Entity
@Data
@Table(name = "Supplier_tbl")
public class Supplier extends BaseEntity<Integer> {
    @Column(name = "name", nullable = false)
    private String name;

    @Override
    public String toString() {
        return "Base{" +
                "Supplier='" + name  + super.toString();
    }
}