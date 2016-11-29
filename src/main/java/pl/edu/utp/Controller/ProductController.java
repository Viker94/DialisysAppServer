package pl.edu.utp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.utp.Entity.Product;
import pl.edu.utp.Enums.Category;
import pl.edu.utp.Repository.ProductRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 26.11.2016.
 */
@RestController
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping("/products")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @PostConstruct
    public void addProducts(){
        Product p1 = new Product(null, "Banan", 4.0, 5.0, 3.2, Category.FRUIT);
        Product p2 = new Product(null, "Japko", 2.3, 1.1, 2.2, Category.FRUIT);
        Product p3 = new Product(null, "Marchew", 1.1, 2.2, 0.2, Category.VEGETABLE);
        Product p4 = new Product(null, "Pomidor", 0.2, 1.0, 0.5, Category.VEGETABLE);
        Product p5 = new Product(null, "Drób", 1.0, 1.5, 2.0, Category.MEAT);
        Product p6 = new Product(null, "Wołowina", 1.5, 2.0, 2.2, Category.MEAT);
        Product p7 = new Product(null, "Chleb", 0.7, 1.0, 1.1, Category.BREAD);
        Product p8 = new Product(null, "Bułka", 0.1, 0.2, 0.1, Category.BREAD);
        Product p9 = new Product(null, "Jogurt", 0.2, 0.2, 0.3, Category.DAIRY);
        Product p10 = new Product(null, "Mleko", 0.5, 0.1, 0.4, Category.DAIRY);
        Product p11 = new Product(null, "Kasza gryczana", 0.7, 1.0, 2.4, Category.CEREAL);
        Product p12 = new Product(null, "Kasza jęczmienna", 0.5, 1.1, 1.4, Category.CEREAL);
        Product p13 = new Product(null, "Lizak", 0.1, 0.1, 0.4, Category.SWEAT);
        Product p14 = new Product(null, "Czekolada", 1.0, 1.0, 1.0, Category.SWEAT);
        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);
        productRepository.save(p5);
        productRepository.save(p6);
        productRepository.save(p7);
        productRepository.save(p8);
        productRepository.save(p9);
        productRepository.save(p10);
        productRepository.save(p11);
        productRepository.save(p12);
        productRepository.save(p13);
        productRepository.save(p14);
    }
}
