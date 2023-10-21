package ch.csbe.productstore.categories;

import ch.csbe.productstore.products.Products;
import ch.csbe.productstore.products.ProductsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<CategoriesDto> getAllCategories(){
        return categoriesRepository.findAll().stream().map(this::toCategoriesDto).toList();
    }

    public CategoriesDto getCategorybyId(long id){
        Categories category = categoriesRepository.findById(id).orElse(null);
        return toCategoriesDto(category);
    }

    public Categories createCategory(Categories category){
        return categoriesRepository.save(category);
    }

    public String updateCategory(long id, Categories category){
        Categories existingCategory = categoriesRepository.findById(id).orElse(null);
        if (existingCategory != null){
            existingCategory.setName(category.getName());
            existingCategory.setActive(category.getActive());
            categoriesRepository.save(existingCategory);
            return "{existingCategory.getid()} was modified";
        }
        else{
            return null;
        }
    }

    public List<ProductsDto> getAllProductsFromCategory(long id){
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
        }
        else{
            return null;
        }
    }

    public Categories deleteCategory(long id) {
        categoriesRepository.deleteById(id);
        return null;
    }

    private CategoriesDto toCategoriesDto(Categories category){
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