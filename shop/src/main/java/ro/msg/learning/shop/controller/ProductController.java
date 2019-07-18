package ro.msg.learning.shop.controller;

import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/all", headers = "Accept=application/json")
    public List<ProductDTO> allProducts() {
        List<Product>products=productService.findAll();
        return products.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", headers = "Accept=application/json")
    public ProductDTO getProductById(@PathVariable("id") Integer id) {
        return new ProductDTO(productService.findByID(id));
    }

    @PostMapping("/")
    public ProductDTO createProduct(@RequestBody ProductDTO product) {
        //I need to find the ProductCategory and Supplier so I use the DTOToProduct from product service
        //that allows me to get to said classes repos
        Product productToSave=productService.DTOToProduct(product);
        productService.saveProduct(productToSave);
        return product;
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO product,@PathVariable("id") Integer id) {
        return productService.ProductToDTO(productService.updateProduct(id,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getWeight(),
                product.getCategoryID(),
                product.getSupplierID(),
                product.getImageUrl()));
    }
}