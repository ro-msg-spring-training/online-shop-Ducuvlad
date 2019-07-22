package ro.msg.learning.shop.model;

import lombok.*;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;/*

 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "Customer_tbl")
public class Customer extends BaseEntity<Integer> {
    @Column(name = "firstname", nullable = false)
    private String firstName;
    @Column(name = "lastname", nullable = false)
    private String lastName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "username", nullable = false)
    private String username;


    @Override
    public String toString() {
        return "Base{" +
                "Customer='" + firstName  +
                " " + lastName + '\'' +
                ", username=" + username +
                "} " + super.toString();
    }
}
