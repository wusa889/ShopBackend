package ch.csbe.productstore.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository Interface for products
 */
@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

}
