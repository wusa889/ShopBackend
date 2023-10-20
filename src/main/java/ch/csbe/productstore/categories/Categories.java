package ch.csbe.productstore.categories;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "tinyint")
    private String active;

    @Column(columnDefinition = "varchar(255)")
    private String name;

    @OneToMany()
    private List<ch.csbe.productstore.products.Products> productsRepositories;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ch.csbe.productstore.products.Products> getProductsRepositories() {
        return productsRepositories;
    }

    public void setProductsRepositories(List<ch.csbe.productstore.products.Products> productsRepositories) {
        this.productsRepositories = productsRepositories;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
