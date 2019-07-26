package ro.msg.learning.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;/*

 */

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @Column(name = "emailaddress", nullable = false)
    private String emailAddress;

    @Override
    public String toString() {
        return "Base{" +
                "Customer='" + firstName +
                " " + lastName + '\'' +
                ", username=" + username + '\'' +
                ", username=" + emailAddress +
                "} " + super.toString();
    }
}
