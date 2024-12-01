package no.kristiania.services;

import no.kristiania.exceptions.ProductNotFoundException;
import no.kristiania.repositories.products.Product;
import no.kristiania.repositories.products.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    //Read
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    //Create
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    //Update

    public void updateProduct(Product product) {
        productRepo.save(product);
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    //Delete
    public void deleteProduct(Long id) {
        if (!productRepo.existsById(id)) {
            throw new ProductNotFoundException("Product not found for ID: " + id);
        }
        productRepo.deleteById(id);
    }

    public void deleteAllProducts() {
        productRepo.deleteAll();
    }

}
