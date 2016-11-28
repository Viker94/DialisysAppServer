package pl.edu.utp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.utp.Entity.Product;

/**
 * Created by Marcin on 26.11.2016.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
