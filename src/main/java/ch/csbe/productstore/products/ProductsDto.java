package ch.csbe.productstore.products;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Data Transfer Object (DTO) representing a product.
 * Used to transfer data between the application layers without exposing the entire domain model.
 */
@RepositoryRestResource(exported = false)
public class ProductsDto {

    // Unique identifier for the category
    private long id;

    // Name of the product
    @NotBlank
    private String name;

    // image of Product
    private String image;

    // Description of product
    private String description;

    // Price of product
    @NotBlank
    private float price;

    // Stock of product
    @NotBlank
    private int stock;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
