package ch.csbe.productstore.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsible for handling HTTP requests related to products.
 * Provides endpoints to manage products and to view products.
 */
@RestController
@RequestMapping("/product")
public class ProductsController {

    // Service class instance responsible for operations related to products
    @Autowired
    private ProductsService productsService;

    @GetMapping()
    public List<ProductsDto> getAllProducts() {
        // Gets all products.
        return productsService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductsDto getProductById(@PathVariable int id) {
        // Gets a single product by its ID.
        return productsService.getProductById(id);
    }

    @PostMapping("/{id}")
    public String createProduct(@RequestBody Products product, @PathVariable long id) {
        // Creates a new product with a category ID as a parameter.
        return productsService.createProduct(product, id);
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable long id, @RequestBody Products product) {
        // Updates an existing product identified by its ID.
        productsService.updateProduct(id, product);
        return "id: " + product.getId() + " " + product.getName() + " was Updated";
    }

    @DeleteMapping("/{id}")
    public Products deleteProduct(@PathVariable long id) {
        // Deletes a product identified by its ID.
        return productsService.deleteProduct(id);
    }
}