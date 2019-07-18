package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.SupplierRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;
    private SupplierRepository supplierRepository;

    public ProductService(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.supplierRepository = supplierRepository;
    }

    public List<Product> findAll() {
        return  productRepository.findAll();
    }

    public Product findByID(int ID) {
        Optional<Product> Product = productRepository.findById(ID);
        return Product.orElse(null);

    }
    @Transactional
    public Product updateProduct(Integer ProductID, String name, String description,
                                 BigDecimal price, double weight, Integer productCategoryID, Integer supplierID,String imageUrl) {

        Optional<Product> Product = productRepository.findById(ProductID);

        Product.ifPresent(product -> {
                    product.setDescription(description);
                    product.setName(name);
                    product.setPrice(price);
                    product.setWeight(weight);
                    product.setImageUrl(imageUrl);
                    product.setProductCategory(productCategoryRepository.getOne(productCategoryID));
                    //productCategory.setName();
                    //productCategory.getDescription();
                    product.setSupplier(supplierRepository.getOne(supplierID));
        });

        return Product.orElse(null);
    }


    public Product saveProduct(Product Product) {
        return productRepository.save(Product);
    }

    public void deleteProduct(int ProductId){
        productRepository.deleteById(ProductId);
    }
    

    public ProductDTO ProductToDTO(Product product) {
        ProductCategory productCategory = product.getProductCategory();
        return new ProductDTO(product);
    }
    public Product DTOToProduct(ProductDTO productDTO) {

        Product product = new Product(
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getWeight(),
                productCategoryRepository.getOne(productDTO.getCategoryID()),
                supplierRepository.getOne(productDTO.getSupplierID()),
                productDTO.getImageUrl()
        );
        return product;
    }

}
