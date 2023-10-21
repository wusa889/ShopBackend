package ch.csbe.productstore.products;

import ch.csbe.productstore.categories.Categories;
import ch.csbe.productstore.categories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {

    public String sayHelloWorld(){
        return "Hello World";
    }

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<ProductsDto> getAllProducts(){
       return productsRepository.findAll().stream().map(this::toProductsDto).toList();
    }

    public ProductsDto getProductById(long id) {
        Products product = productsRepository.findById(id).orElse(null);
        return toProductsDto(product);
    }

    public String createProduct(Products product, long id){
        Categories category = categoriesRepository.findById(id).orElse(null);
        if (category != null){
            product.setCategoriesRepository(category);
            productsRepository.save(product);
            return "product created";
        }
        else{
            throw new RuntimeException("Kategorie nicht gefunden");
        }

    }

    public Products updateProduct(long id, Products product){
        Products existingProduct = productsRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setDescription(product.getDescription());
            existingProduct.setName(product.getName());
            existingProduct.setImage(product.getImage());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setSku(product.getSku());
            existingProduct.setStock(existingProduct.getStock());
            return productsRepository.save(existingProduct);
        }
        else {
            return null;
        }

    }

    public Products deleteProduct(Long id){
        productsRepository.deleteById(id);
        return null;
    }

    private ProductsDto toProductsDto(Products products){
        ProductsDto productsDto = new ProductsDto();
        productsDto.setId(products.getId());
        productsDto.setDescription(products.getDescription());
        productsDto.setImage(products.getImage());
        productsDto.setName(products.getName());
        productsDto.setPrice(products.getPrice());
        productsDto.setStock(products.getStock());
        return productsDto;
    }



}
