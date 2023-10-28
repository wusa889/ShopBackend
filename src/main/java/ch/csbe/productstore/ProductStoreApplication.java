package ch.csbe.productstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Main class of Applikation
 */
@RepositoryRestResource(exported = false)
@SpringBootApplication
public class ProductStoreApplication {

    /**
     * Main Method of application
     */
    public static void main(String[] args) {
        SpringApplication.run(ProductStoreApplication.class, args);
    }

}
