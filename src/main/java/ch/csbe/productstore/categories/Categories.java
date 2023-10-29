package ch.csbe.productstore.categories;

import ch.csbe.productstore.products.Products;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource(exported = false)
/**
 * Represents a category in the Database.
 * Each category can have multiple products associated with it.
 */
@Entity
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Provides specification of generation strategies for primary keys.
    private Long id;

    // Represents Active Status
    @NotBlank
    @Column(columnDefinition = "tinyint")
    private int active;

    // Name of the category
    @NotBlank
    @Column(columnDefinition = "varchar(255)")
    private String name;

    // List of products associated with this category
    @OneToMany(mappedBy = "category")
    private List<Products> productsRepository;

    // Getters and Setters

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Products> getProductsRepositories() {
        return productsRepository;
    }

    // Set the list of products associated with this category
    public void setProductsRepositories(List<Products> products) {
        this.productsRepository = productsRepository;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}