package ch.csbe.productstore.products;

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

    public List<Products> getAllProducts(){
        return productsRepository.findAll();
    }

    public Products getProductById(long id) {
        return productsRepository.findById(id).orElse(null);
    }

    public Products createProduct(Products product){
        return productsRepository.save(product);
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



}
