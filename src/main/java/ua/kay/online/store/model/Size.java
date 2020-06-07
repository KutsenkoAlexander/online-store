package ua.kay.online.store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Cacheable
@Entity
@Table(name = "spr_size")
public class Size extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 2227948084905830907L;

    @Column(name = "name", nullable = false, unique = true)
    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "size", fetch = FetchType.LAZY)
    private Set<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

}
