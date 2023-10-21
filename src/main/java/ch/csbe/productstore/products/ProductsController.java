package ch.csbe.productstore.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductsController {
    @Autowired
    private ProductsService productsService;
    @GetMapping()
    public List<Products> getAllProducts(){
        return productsService.getAllProducts();
    }
    @GetMapping("/{id}")
    public Products getProductById(@PathVariable int id){
        return productsService.getProductById(id);
    }

    @PostMapping()
    public Products createProduct(@RequestBody Products product) {
        return productsService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Products updateProduct(@PathVariable long id, @RequestBody Products product){
        return productsService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public Products deleteProduct(@PathVariable long id){
        return productsService.deleteProduct(id);
    }

}
