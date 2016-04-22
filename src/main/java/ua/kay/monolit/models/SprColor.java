package ua.kay.monolit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "spr_color", schema = "", catalog = "monolit")
public class SprColor implements Serializable {

    private static final long serialVersionUID = 2498157470937909959L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_spr_colors", nullable = false, insertable = true, updatable = true)
    private int idSprColors;

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 45)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "sprColorByColorId")
    private Collection<Product> productsByIdSprColors;

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

    public Collection<Product> getProductsByIdSprColors() {
        return productsByIdSprColors;
    }

    public void setProductsByIdSprColors(Collection<Product> productsByIdSprColors) {
        this.productsByIdSprColors = productsByIdSprColors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SprColor sprColor = (SprColor) o;

        if (idSprColors != sprColor.idSprColors) return false;
        return name != null ? name.equals(sprColor.name) : sprColor.name == null;

    }

    @Override
    public int hashCode() {
        int result = idSprColors;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

}
