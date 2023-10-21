package ch.csbe.productstore.categories;

import ch.csbe.productstore.products.ProductsDto;

import java.util.List;

public class CategoriesDto {
    private long id;

    private String name;

    private List<ProductsDto> products;

    private int active;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductsDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsDto> products) {
        this.products = products;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
