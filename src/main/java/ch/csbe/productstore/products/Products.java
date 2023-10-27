package ch.csbe.productstore.products;

import ch.csbe.productstore.categories.Categories;
import jakarta.persistence.*;

@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(100)")
    private String sku;

    @Column(columnDefinition = "tinyint")
    private int active;

    @Column(columnDefinition = "varchar(255)")
    private String name;

    @Column(columnDefinition = "varchar(1000)")
    private String image;

    @Column(columnDefinition = "mediumtext")
    private String description;

    @Column(columnDefinition = "float")
    private float price;

    @Column(columnDefinition = "int")
    private int stock;

    @ManyToOne()
    private Categories category;

    public Products() {
        //default Constructor
    }

    public Products(String sku, int active, String name, String image, String description, float price, int stock) {
        this.sku = sku;
        this.active = active;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories categoriesRepository) {
        this.category = categoriesRepository;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
