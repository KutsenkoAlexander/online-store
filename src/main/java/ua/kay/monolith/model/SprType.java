package ua.kay.monolith.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Cacheable
@Entity
@Table(name = "spr_type")
public class SprType implements Serializable {

    private static final long serialVersionUID = -5835688435246448602L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_spr_type", nullable = false)
    private int idSprType;

    @Column(name = "name", nullable = false, unique = true)
    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "sprType", fetch = FetchType.LAZY)
    private Set<Product> products;

    public int getIdSprType() {
        return idSprType;
    }

    public void setIdSprType(int idSprType) {
        this.idSprType = idSprType;
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
