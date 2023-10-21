package ch.csbe.productstore.categories;

import ch.csbe.productstore.products.Products;
import ch.csbe.productstore.products.ProductsDto;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "tinyint")
    private int active;

    @Column(columnDefinition = "varchar(255)")
    private String name;

    @OneToMany(mappedBy = "categoriesRepository")
    private List<Products> productsRepository;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Products> getProductsRepositories() {
        return productsRepository;
    }

    public void setProductsRepositories(List<Products> products) {
        this.productsRepository = productsRepository;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
