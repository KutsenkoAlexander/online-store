package ua.kay.monolit.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name = "img_category", schema = "", catalog = "monolit")
public class ImgCategory implements Serializable {

    private static final long serialVersionUID = -7045209386016632304L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_img_category")
    private long idImgCategory;

    @Basic
    @Column(name = "images")
    private byte[] images;

    @Basic
    @Column(name = "category_id")
    private int categoryId;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id_category", nullable = false, updatable = false, insertable = false)
    private SprCategory sprCategoryByCategoryId;

    public long getIdImgCategory() {
        return idImgCategory;
    }

    public void setIdImgCategory(long idImgCategory) {
        this.idImgCategory = idImgCategory;
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public SprCategory getSprCategoryByCategoryId() {
        return sprCategoryByCategoryId;
    }

    public void setSprCategoryByCategoryId(SprCategory sprCategoryByCategoryId) {
        this.sprCategoryByCategoryId = sprCategoryByCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImgCategory that = (ImgCategory) o;

        if (idImgCategory != that.idImgCategory) return false;
        if (categoryId != that.categoryId) return false;
        if (!Arrays.equals(images, that.images)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idImgCategory ^ (idImgCategory >>> 32));
        result = 31 * result + categoryId;
        result = 31 * result + (images != null ? Arrays.hashCode(images) : 0);
        return result;
    }

}
