package ua.kay.monolit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Cacheable
@Entity
@Table(name = "spr_size", catalog = "monolit")
public class SprSize implements Serializable {

    private static final long serialVersionUID = 2227948084905830907L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_spr_size", nullable = false)
    private int idSprSize;

    @Column(name = "name", nullable = false, unique = true)
    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "sprSize", fetch = FetchType.LAZY)
    private Set<Product> products;

    public int getIdSprSize() {
        return idSprSize;
    }

    public void setIdSprSize(int idSprSize) {
        this.idSprSize = idSprSize;
    }

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
