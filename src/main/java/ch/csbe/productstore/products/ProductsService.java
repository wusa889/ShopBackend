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

    public List<ch.csbe.productstore.products.Products> getAllProducts(){
        return productsRepository.findAll();
    }

    public ch.csbe.productstore.products.Products getProductById(long id) {
        return productsRepository.findById(id).orElse(null);
    }

    public ch.csbe.productstore.products.Products createProduct(ch.csbe.productstore.products.Products product){
        return productsRepository.save(product);
    }

    public ch.csbe.productstore.products.Products updateProduct(long id, ch.csbe.productstore.products.Products product){
        ch.csbe.productstore.products.Products existingProduct = productsRepository.findById(id).orElse(null);
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

    public void deleteBook(Long id){
        productsRepository.deleteById(id);
    }



}
