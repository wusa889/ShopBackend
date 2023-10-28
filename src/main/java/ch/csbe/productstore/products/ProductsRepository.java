package ch.csbe.productstore.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Repository Interface for products
 */
@RepositoryRestResource(exported = false)
@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

}
