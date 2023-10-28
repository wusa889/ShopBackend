package ch.csbe.productstore.categories;

import ch.csbe.productstore.products.ProductsDto;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource(exported = false)
/**
 * Data Transfer Object (DTO) representing a product category.
 * Used to transfer data between the application layers without exposing the entire domain model.
 */
public class CategoriesDto {

    // Unique identifier for the category
    private long id;

    // Name of the category
    private String name;

    // List of products associated with the category
    private List<ProductsDto> products;

    // Status indicator for the category (e.g., active/inactive)
    private int active;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductsDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsDto> products) {
        this.products = products;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
