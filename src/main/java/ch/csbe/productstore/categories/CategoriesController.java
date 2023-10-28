package ch.csbe.productstore.categories;

import ch.csbe.productstore.products.Products;
import ch.csbe.productstore.products.ProductsDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsible for handling HTTP requests related to product categories.
 * Provides endpoints to manage categories and to fetch products associated with a specific category.
 */
@RestController
@Tag(name = "CategoriesController")
@RequestMapping("/category")
public class CategoriesController {

    // Service class instance responsible for operations related to categories
    @Autowired
    private CategoriesService categoriesService;

    /**
     * Fetches a list of all categories.
     *
     * @return List of DTO representations of all categories.
     */
    @GetMapping()
    public List<CategoriesDto> getAllCategories() {
        return categoriesService.getAllCategories();
    }

    /**
     * Fetches a specific category based on its unique ID.
     *
     * @param id The unique ID of the category.
     * @return DTO representation of the category.
     */
    @GetMapping("/{id}")
    public CategoriesDto getCategoryById(@PathVariable long id) {
        return categoriesService.getCategorybyId(id);
    }

    /**
     * Fetches all products associated with a specific category.
     *
     * @param id The unique ID of the category.
     * @return List of DTO representations of products.
     */
    @GetMapping("/{id}/products")
    public List<ProductsDto> getAllProductsFromCategory(@PathVariable long id) {
        return categoriesService.getAllProductsFromCategory(id);
    }

    /**
     * Creates a new category.
     *
     * @param category The category entity to be created.
     * @return The created category entity.
     */
    @PostMapping()
    public Categories createCategory(@RequestBody Categories category) {
        return categoriesService.createCategory(category);
    }

    /**
     * Updates an existing category based on its unique ID.
     *
     * @param category The category entity with updated details.
     * @param id       The unique ID of the category to be updated.
     * @return Status message indicating success or failure of the update operation.
     */
    @PutMapping("/{id}")
    public String updateCategory(@RequestBody Categories category, @PathVariable long id) {
        return categoriesService.updateCategory(id, category);
    }

    /**
     * Deletes a category based on its unique ID.
     *
     * @param id The unique ID of the category to be deleted.
     * @return The deleted category entity.
     */
    @DeleteMapping("/{id}")
    public Categories deleteCategory(@PathVariable long id) {
        return categoriesService.deleteCategory(id);
    }
}