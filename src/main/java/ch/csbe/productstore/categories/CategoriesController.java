package ch.csbe.productstore.categories;

import ch.csbe.productstore.products.Products;
import ch.csbe.productstore.products.ProductsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsible for handling HTTP requests related to product categories.
 * Provides endpoints to manage categories and to fetch products associated with a specific category.
 */
@RestController
@Tag(name = "CategoriesController",
        description = "Controller für Kategorie Aktionen")
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
    @Operation(summary = "Gibt eine Liste aller Kategorien zurück",
            description = "Alle Kategorien die vorhanden sind werden als Liste ausgegeben")
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
    @Operation(summary = "Gibt ein Kategorie anhand der ID zurück",
            description = "Gibt das Kategorie mit der entsprechenden ID zurück")
    public CategoriesDto getCategoryById(
            @Parameter(description = "Die ID der Kategorie die ausgegeben werden soll")
            @PathVariable long id
    ) {
        return categoriesService.getCategorybyId(id);
    }

    /**
     * Fetches all products associated with a specific category.
     *
     * @param id The unique ID of the category.
     * @return List of DTO representations of products.
     */
    @GetMapping("/{id}/products")
    @Operation(summary = "Gibt alle Produkte einer Kategorie anhand der ID der Kategorie zurück",
            description = "Gibt alle Produke als Liste einer Kategorie anhand der ID aus")
    public List<ProductsDto> getAllProductsFromCategory(
            @Parameter(description = "Die ID der Kategorie von welcher die Produkte aufgelistet werden sollen")
            @PathVariable long id) {
        return categoriesService.getAllProductsFromCategory(id);
    }

    /**
     * Creates a new category.
     *
     * @param category The category entity to be created.
     * @return The created category entity.
     */
    @PostMapping()
    @Operation(summary = "Erlaubt es dem Administrator neue Produkte zu erstellen mit dazugehöriger Kategorie",
            description = "Erstellt ein Prdodukt mit dazugehöriger Kategorie")
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
    @Operation(summary = "Erlaubt es dem Administrator Kategorien zu verändern",
            description = "Ändert eine bestehende Kategorie anhand ID und JSON Requestbody")
    public String updateCategory(
            @Parameter(description = "Die ID der Kategorie welche geändert werden soll")
            @RequestBody Categories category, @PathVariable long id
    ) {
        return categoriesService.updateCategory(id, category);
    }

    /**
     * Deletes a category based on its unique ID.
     *
     * @param id The unique ID of the category to be deleted.
     * @return The deleted category entity.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Erlaubt es dem Administrator Kategorien zu löschen",
            description = "Löscht eine bestehende Kategorie anhand der ID")
    public Categories deleteCategory(
            @Parameter(description = "Die ID der Kategorie welche gelöscht werden soll")
            @PathVariable long id
    ) {
        return categoriesService.deleteCategory(id);
    }
}