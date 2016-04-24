package ua.kay.monolit.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "full_product", schema = "", catalog = "monolit")
public class FullProduct implements Serializable {

    private static final long serialVersionUID = -2105560949052786522L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_full_product")
    private long idFullProduct;

    @Basic
    @Column(name = "product_id")
    private long productId;

    @Basic
    @Column(name = "image_id", nullable = true)
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id_image", nullable = true, updatable = false, insertable = false)
    private Image imageByImageId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id_product", nullable = false, updatable = false, insertable = false)
    private Product productByProductId;

    public long getIdFullProduct() {
        return idFullProduct;
    }

    public void setIdFullProduct(long idFullProduct) {
        this.idFullProduct = idFullProduct;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Image getImageByImageId() {
        return imageByImageId;
    }

    public void setImageByImageId(Image imageByImageId) {
        this.imageByImageId = imageByImageId;
    }

    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FullProduct that = (FullProduct) o;

        if (idFullProduct != that.idFullProduct) return false;
        if (productId != that.productId) return false;
        if (imageId != null ? !imageId.equals(that.imageId) : that.imageId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idFullProduct ^ (idFullProduct >>> 32));
        result = 31 * result + (int) (productId ^ (productId >>> 32));
        result = 31 * result + (imageId != null ? imageId.hashCode() : 0);
        return result;
    }

}
