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
    public List<ProductsDto> getAllProducts(){
        return productsService.getAllProducts();
    }
    @GetMapping("/{id}")
    public ProductsDto getProductById(@PathVariable int id){
        return productsService.getProductById(id);
    }

    @PostMapping("/{id}")
    public String createProduct(@RequestBody Products product, @PathVariable long id) {
        return productsService.createProduct(product, id);
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable long id, @RequestBody Products product){
        productsService.updateProduct(id, product);
        return("id: " + product.getId()+ " " + product.getName() + " was Updated");

    }

    @DeleteMapping("/{id}")
    public Products deleteProduct(@PathVariable long id){
        return productsService.deleteProduct(id);
    }

}
