package ch.csbe.productstore.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<ch.csbe.productstore.categories.Categories, Long>{

}