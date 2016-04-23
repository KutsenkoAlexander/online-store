package ua.kay.monolit.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name = "img_product", schema = "", catalog = "monolit")
public class ImgProduct implements Serializable {

    private static final long serialVersionUID = -2105560949052786522L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_img_product")
    private long idImgProduct;

    @Basic
    @Column(name = "image", nullable = true)
    private byte[] image;

    @Basic
    @Column(name = "product_id")
    private long productId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id_product", nullable = false, updatable = false, insertable = false)
    private Product productByProductId;

    public long getIdImgProduct() {
        return idImgProduct;
    }

    public void setIdImgProduct(long idImgProduct) {
        this.idImgProduct = idImgProduct;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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

        ImgProduct that = (ImgProduct) o;

        if (idImgProduct != that.idImgProduct) return false;
        if (productId != that.productId) return false;
        if (!Arrays.equals(image, that.image)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idImgProduct ^ (idImgProduct >>> 32));
        result = 31 * result + (int) (productId ^ (productId >>> 32));
        result = 31 * result + (image != null ? Arrays.hashCode(image) : 0);
        return result;
    }

}
