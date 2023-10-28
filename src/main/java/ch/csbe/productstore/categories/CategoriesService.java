package ch.csbe.productstore.categories;

import ch.csbe.productstore.products.Products;
import ch.csbe.productstore.products.ProductsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for logic related to Categories.
 */
@RepositoryRestResource(exported = false)
@Service
public class CategoriesService {

    // Creating instance of CategoriesRepository
    @Autowired
    private CategoriesRepository categoriesRepository;

    /**
     * Retrieves all categories from the database.
     *
     * @return List of CategoriesDto representing all categories.
     */
    public List<CategoriesDto> getAllCategories() {
        return categoriesRepository.findAll().stream().map(this::toCategoriesDto).toList();
    }

    /**
     * Retrieves a specific category by its ID.
     *
     * @param id The ID of the desired category.
     * @return CategoriesDto representation of the category.
     */
    public CategoriesDto getCategorybyId(long id) {
        Categories category = categoriesRepository.findById(id).orElse(null);
        return toCategoriesDto(category);
    }

    /**
     * Saves a new category to the database.
     *
     * @param category The category to be saved.
     * @return The saved category.
     */
    public Categories createCategory(Categories category) {
        return categoriesRepository.save(category);
    }

    /**
     * Updates an existing category by ID.
     *
     * @param id       The ID of the category to be updated.
     * @param category Updated category data.
     * @return A message indicating the success or failure of the update operation.
     */
    public String updateCategory(long id, Categories category) {
        Categories existingCategory = categoriesRepository.findById(id).orElse(null);
        if (existingCategory != null) {
            existingCategory.setName(category.getName());
            existingCategory.setActive(category.getActive());
            categoriesRepository.save(existingCategory);
            return "{existingCategory.getid()} was modified";
        } else {
            return null;
        }
    }

    /**
     * Retrieves all products associated with a given category.
     *
     * @param id The ID of the category.
     * @return List of ProductsDto representing products of the category.
     */
    public List<ProductsDto> getAllProductsFromCategory(long id) {
        Categories category = categoriesRepository.findById(id).orElse(null);
        if (category != null) {
            return category.getProductsRepositories().stream().map(Products -> {
                ProductsDto productsDto = new ProductsDto();
                productsDto.setId(Products.getId());
                productsDto.setStock(Products.getStock());
                productsDto.setName(Products.getName());
                productsDto.setPrice(Products.getPrice());
                productsDto.setDescription(Products.getDescription());
                productsDto.setImage(Products.getImage());
                return productsDto;
            }).toList();
        } else {
            return null;
        }
    }

    /**
     * Deletes a category by its ID from the database.
     *
     * @param id The ID of the category to be deleted.
     * @return Always null. (This behavior may need adjustment.)
     */
    public Categories deleteCategory(long id) {
        categoriesRepository.deleteById(id);
        return null;
    }

    /**
     * Converts a Categories entity to its DTO representation.
     *
     * @param category The category entity to be converted.
     * @return CategoriesDto representation of the category.
     */
    private CategoriesDto toCategoriesDto(Categories category) {
        CategoriesDto categoriesDto = new CategoriesDto();
        categoriesDto.setId(category.getId());
        categoriesDto.setActive(category.getActive());
        categoriesDto.setProducts(category.getProductsRepositories().stream().map(Products -> {
            ProductsDto productsDto = new ProductsDto();
            productsDto.setId(Products.getId());
            productsDto.setStock(Products.getStock());
            productsDto.setName(Products.getName());
            productsDto.setPrice(Products.getPrice());
            productsDto.setDescription(Products.getDescription());
            productsDto.setImage(Products.getImage());
            return productsDto;
        }).toList());
        categoriesDto.setName(category.getName());
        return categoriesDto;
    }
}