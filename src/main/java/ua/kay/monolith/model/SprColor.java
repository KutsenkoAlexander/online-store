package ua.kay.monolith.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Cacheable
@Entity
@Table(name = "spr_color")
public class SprColor implements Serializable {

    private static final long serialVersionUID = 2498157470937909959L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_spr_colors", nullable = false)
    private int idSprColors;

    @Column(name = "name", nullable = false, unique = true, length = 45)
    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "sprColor", fetch = FetchType.LAZY)
    private Set<Product> products;

    public int getIdSprColors() {
        return idSprColors;
    }

    public void setIdSprColors(int idSprColors) {
        this.idSprColors = idSprColors;
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
