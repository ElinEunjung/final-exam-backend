package no.kristiania.services;

import no.kristiania.repositories.orders.Order;
import no.kristiania.repositories.orders.OrderRepo;
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
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    //Delete
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
