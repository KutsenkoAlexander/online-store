package ua.kay.monolit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "spr_size", schema = "", catalog = "monolit")
public class SprSize implements Serializable {

    private static final long serialVersionUID = 2227948084905830907L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_spr_size", nullable = false, insertable = true, updatable = true)
    private int idSprSize;

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 45)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SprSize sprSize = (SprSize) o;

        if (idSprSize != sprSize.idSprSize) return false;
        return name != null ? name.equals(sprSize.name) : sprSize.name == null;
    }

    @Override
    public int hashCode() {
        int result = idSprSize;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

}
