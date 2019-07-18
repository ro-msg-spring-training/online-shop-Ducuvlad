package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Product;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO implements Serializable {
    public ProductDTO(Product product) {
        this.productID = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.weight =product.getWeight();
        this.imageUrl = product.getImageUrl();
        this.categoryID = product.getProductCategory().getId();
        this.categoryName = product.getProductCategory().getName();
        this.categoryDescription = product.getProductCategory().getDescription();
        this.supplierID = product.getSupplier().getId();
    }

    private Integer productID;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private String imageUrl;
    private Integer categoryID;
    private String categoryName;
    private String categoryDescription;
    private Integer supplierID;
    //private String supplierName;
}