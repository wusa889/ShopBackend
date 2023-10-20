package ch.csbe.productstore.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductsController {
    @Autowired
    private ch.csbe.productstore.products.ProductsService productsService;
    @GetMapping()
    public List<ch.csbe.productstore.products.Products> getAllProducts(){
        return productsService.getAllProducts();
    }
    @GetMapping("/{id}")
    public ch.csbe.productstore.products.Products getProductById(@PathVariable int id){
        return productsService.getProductById(id);
    }

    @PostMapping()
    public ch.csbe.productstore.products.Products createProduct(@RequestBody ch.csbe.productstore.products.Products product) {
        return productsService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ch.csbe.productstore.products.Products updateProduct(@PathVariable long id, @RequestBody ch.csbe.productstore.products.Products product){
        return productsService.updateProduct(id, product);
    }
}
