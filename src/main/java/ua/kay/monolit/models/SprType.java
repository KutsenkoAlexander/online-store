package ua.kay.monolit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "spr_type", schema = "", catalog = "monolit")
public class SprType implements Serializable {

    private static final long serialVersionUID = -5835688435246448602L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_spr_type", nullable = false, insertable = true, updatable = true)
    private int idSprType;

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 255)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "sprTypeByTypeId")
    private Collection<Product> productsByIdSprType;

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

    public Collection<Product> getProductsByIdSprType() {
        return productsByIdSprType;
    }

    public void setProductsByIdSprType(Collection<Product> productsByIdSprType) {
        this.productsByIdSprType = productsByIdSprType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SprType sprType = (SprType) o;

        if (idSprType != sprType.idSprType) return false;
        return name != null ? name.equals(sprType.name) : sprType.name == null;

    }

    @Override
    public int hashCode() {
        int result = idSprType;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

}
