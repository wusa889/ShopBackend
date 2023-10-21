package ch.csbe.productstore.categories;
import ch.csbe.productstore.products.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;
    @GetMapping()
    public List<Categories> getAllCategories(){
        return categoriesService.getAllCategories();
    }
    @GetMapping("/{id}")
    public Categories getCategoryById(@PathVariable long id){
        return categoriesService.getCategorybyId(id);
    }

    @GetMapping("/{id}/products")
    public List<Products> getAllProductsFromCategory(@PathVariable long id){
        return categoriesService.getAllProductsFromCategory(id);
    }

    @PostMapping()
    public Categories createCategory(@RequestBody Categories category){
        return categoriesService.createCategory(category);
    }

    @PutMapping("/{id}")
    public Categories updateCategory(@RequestBody Categories category, @PathVariable long id){
        return categoriesService.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public Categories deleteCategory(@PathVariable long id){
        return categoriesService.deleteCategory(id);
    }


}
