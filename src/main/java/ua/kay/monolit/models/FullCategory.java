package ua.kay.monolit.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "full_category", schema = "", catalog = "monolit")
public class FullCategory implements Serializable {

    private static final long serialVersionUID = -7045209386016632304L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_full_category")
    private long idFullCategory;

    @Basic
    @Column(name = "category_id")
    private int categoryId;

    @Basic
    @Column(name = "image_id")
    private long imageId;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id_category", nullable = false, insertable = false, updatable = false)
    private SprCategory sprCategoryByCategoryId;

    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id_image", nullable = false, insertable = false, updatable = false)
    private Image imageByImageId;

    public long getIdFullCategory() {
        return idFullCategory;
    }

    public void setIdFullCategory(long idFullCategory) {
        this.idFullCategory = idFullCategory;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public SprCategory getSprCategoryByCategoryId() {
        return sprCategoryByCategoryId;
    }

    public void setSprCategoryByCategoryId(SprCategory sprCategoryByCategoryId) {
        this.sprCategoryByCategoryId = sprCategoryByCategoryId;
    }

    public Image getImageByImageId() {
        return imageByImageId;
    }

    public void setImageByImageId(Image imageByImageId) {
        this.imageByImageId = imageByImageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FullCategory that = (FullCategory) o;

        if (idFullCategory != that.idFullCategory) return false;
        if (categoryId != that.categoryId) return false;
        if (imageId != that.imageId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idFullCategory ^ (idFullCategory >>> 32));
        result = 31 * result + categoryId;
        result = 31 * result + (int) (imageId ^ (imageId >>> 32));
        return result;
    }

}
