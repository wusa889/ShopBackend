package ch.csbe.productstore.categories;

import ch.csbe.productstore.products.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<Categories> getAllCategories(){
        return categoriesRepository.findAll();
    }

    public Categories getCategorybyId(long id){
        return categoriesRepository.findById(id).orElse(null);
    }

    public Categories createCategory(Categories category){
        return categoriesRepository.save(category);
    }

    public Categories updateCategory(long id, Categories category){
        Categories existingCategory = categoriesRepository.findById(id).orElse(null);
        if (existingCategory != null){
            existingCategory.setName(category.getName());
            existingCategory.setActive(category.getActive());
            existingCategory.setProductsRepositories(category.getProductsRepositories());
            return categoriesRepository.save(existingCategory);
        }
        else{
            return null;
        }
    }

    public List<Products> getAllProductsFromCategory(long id){
        Categories category = categoriesRepository.findById(id).orElse(null);
        if (category != null) {
            return category.getProductsRepositories();
        }
        else{
            return null;
        }
    }

    public Categories deleteCategory(long id) {
        categoriesRepository.deleteById(id);
        return null;
    }
}