package ua.kay.monolit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "spr_category", schema = "", catalog = "monolit")
public class SprCategory implements Serializable {

    private static final long serialVersionUID = -1444840323390519960L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_category", nullable = false, insertable = true, updatable = true)
    private Integer idCategory;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "parent_id")
    private int parentId;

    @JsonIgnore
    @OneToMany(mappedBy = "sprCategoryByCategoryId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<FullCategory> fullCategoriesByIdCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "sprCategoryByCategoryId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Product> productsByIdCategory;

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Collection<FullCategory> getFullCategoriesByIdCategory() {
        return fullCategoriesByIdCategory;
    }

    public void setFullCategoriesByIdCategory(Collection<FullCategory> fullCategoriesByIdCategory) {
        this.fullCategoriesByIdCategory = fullCategoriesByIdCategory;
    }

    public Collection<Product> getProductsByIdCategory() {
        return productsByIdCategory;
    }

    public void setProductsByIdCategory(Collection<Product> productsByIdCategory) {
        this.productsByIdCategory = productsByIdCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SprCategory that = (SprCategory) o;

        if (idCategory != that.idCategory) return false;
        if (parentId != that.parentId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCategory;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + parentId;
        return result;
    }

}
