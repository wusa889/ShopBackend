package ch.csbe.productstore.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Repository Interface for Categories
 */
@RepositoryRestResource(exported = false)
@Repository
public interface CategoriesRepository extends JpaRepository<ch.csbe.productstore.categories.Categories, Long> {

}