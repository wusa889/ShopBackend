package ch.csbe.productstore.products;

import ch.csbe.productstore.categories.Categories;
import ch.csbe.productstore.categories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service class responsible for logic related to products.
 */
@RepositoryRestResource(exported = false)
@Service
public class ProductsService {

    // Creating instance of ProductsRepository
    @Autowired
    private ProductsRepository productsRepository;

    // Creating instance of CategoriesRepository
    @Autowired
    private CategoriesRepository categoriesRepository;

    /**
     * Retrieves all products from the database and maps them to their DTO representations.
     *
     * @return List of ProductsDto representing all products.
     */
    public List<ProductsDto> getAllProducts() {
        return productsRepository.findAll().stream().map(this::toProductsDto).toList();
    }

    /**
     * Retrieves a product by its ID and maps it to its DTO representation.
     *
     * @param id The ID of the product to retrieve.
     * @return ProductsDto representation of the product.
     */
    public ProductsDto getProductById(long id) {
        Products product = productsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return toProductsDto(product);
    }

    /**
     * Creates a product and associates it with a category.
     *
     * @param product The product entity to be saved.
     * @param id      The ID of the category to associate the product with.
     * @return A message indicating the success of the creation.
     */
    public String createProduct(Products product, long id) {
        Categories category = categoriesRepository.findById(id).orElse(null);
        if (category != null) {
            product.setCategory(category);
            productsRepository.save(product);
            return "product created";
        } else {
            throw new RuntimeException("Kategorie nicht gefunden");
        }
    }

    /**
     * Updates an existing product in the database.
     *
     * @param id      The ID of the product to update.
     * @param product The updated product details.
     * @return A message indicating the success of the update.
     */
    public String updateProduct(long id, Products product) {
        Products existingProduct = productsRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setDescription(product.getDescription());
            existingProduct.setName(product.getName());
            existingProduct.setImage(product.getImage());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setSku(product.getSku());
            existingProduct.setStock(existingProduct.getStock());
            productsRepository.save(existingProduct);
            return (existingProduct.getName() + " was updated");
        } else {
            return null;
        }
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete.
     * @return Null (this can be adjusted based on use-case).
     */
    public Products deleteProduct(Long id) {
        productsRepository.deleteById(id);
        return null;
    }

    /**
     * Converts a product entity to its DTO representation.
     *
     * @param products The product entity.
     * @return ProductsDto representation of the given product.
     */
    private ProductsDto toProductsDto(Products products) {
        ProductsDto productsDto = new ProductsDto();
        productsDto.setId(products.getId());
        productsDto.setDescription(products.getDescription());
        productsDto.setImage(products.getImage());
        productsDto.setName(products.getName());
        productsDto.setPrice(products.getPrice());
        productsDto.setStock(products.getStock());
        return productsDto;
    }
}
