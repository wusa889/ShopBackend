package ch.csbe.productstore.products;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsible for handling HTTP requests related to products.
 * Provides endpoints to manage products and to view products.
 */
@RestController
@Tag(name = "ProductsController")
@RequestMapping("/product")
public class ProductsController {

    // Service class instance responsible for operations related to products
    @Autowired
    private ProductsService productsService;

    @GetMapping()
    @Operation(summary = "Gibt eine Liste aller Produkte zurück", description = "Alle Produkte die vorhanden sind werden als Liste ausgegeben")
    public List<ProductsDto> getAllProducts() {
        // Gets all products.
        return productsService.getAllProducts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gibt ein Produkt anhand der ID zurück", description = "Gibt das Produkt mit der entsprechenden ID zurück")
    public ProductsDto getProductById(@PathVariable int id) {
        // Gets a single product by its ID.
        return productsService.getProductById(id);
    }

    @PostMapping("/{id}")
    @Operation(summary = "Erlaubt es dem Administrator neue Produkte zu erstellen mit dazugehöriger Kategorie", description = "Erstellt ein Prdodukt mit dazugehöriger Kategorie")
    public String createProduct(
            @Parameter(description = "Die ID der Kategorie die zu dem Produkt gehören soll")
            @RequestBody Products product, @PathVariable long id
    ){
        // Creates a new product with a category ID as a parameter.
        return productsService.createProduct(product, id);
    }
    @PostMapping()
    @Operation(summary = "Erlaubt es dem Administrator ein neues Produkte zu erstellen", description = "Gibt das Produkt mit der entsprechenden ID zurück")
    public String createProductWithoutId(
            @Parameter(description = "Die ID der Kategorie die zu dem Produkt gehören soll")
            @RequestBody Products product
    ){
        // Creates a new product with a category ID as a parameter.
        return productsService.createProductWithoutId(product);
    }

    @PutMapping("/{id}")
    public String updateProduct(
            @Parameter(description = "Die ID des Produktes welches geändert werden soll")
            @PathVariable long id, @RequestBody Products product
    ){
        // Updates an existing product identified by its ID.
        productsService.updateProduct(id, product);
        return "id: " + product.getId() + " " + product.getName() + " was Updated";
    }

    @DeleteMapping("/{id}")
    public Products deleteProduct(
            @Parameter(description = "Die ID des Produktes welches gelöscht werden soll")
            @PathVariable long id
    ){
        // Deletes a product identified by its ID.
        return productsService.deleteProduct(id);
    }
}