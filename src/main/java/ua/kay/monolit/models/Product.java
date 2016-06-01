package ua.kay.monolit.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "", catalog = "monolit")
public class Product implements Serializable {

    private static final long serialVersionUID = -7651298149649100262L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_product", nullable = false, insertable = true, updatable = true)
    private long idProduct;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Basic
    @Column(name = "exist")
    private byte exist;

    @ManyToOne
    @JoinColumn(name = "image_id", nullable = true)
    private Image image;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private SprCategory sprCategory;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = true)
    private SprColor sprColor;

    @ManyToOne
    @JoinColumn(name = "consumer_id", nullable = true)
    private SprConsumer sprConsumer;

    @ManyToOne
    @JoinColumn(name = "size_id", nullable = true)
    private SprSize sprSize;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = true)
    private SprType sprType;

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte getExist() {
        return exist;
    }

    public void setExist(byte exist) {
        this.exist = exist;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public SprCategory getSprCategory() {
        return sprCategory;
    }

    public void setSprCategory(SprCategory sprCategory) {
        this.sprCategory = sprCategory;
    }

    public SprColor getSprColor() {
        return sprColor;
    }

    public void setSprColor(SprColor sprColor) {
        this.sprColor = sprColor;
    }

    public SprConsumer getSprConsumer() {
        return sprConsumer;
    }

    public void setSprConsumer(SprConsumer sprConsumer) {
        this.sprConsumer = sprConsumer;
    }

    public SprSize getSprSize() {
        return sprSize;
    }

    public void setSprSize(SprSize sprSize) {
        this.sprSize = sprSize;
    }

    public SprType getSprType() {
        return sprType;
    }

    public void setSprType(SprType sprType) {
        this.sprType = sprType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct, productCode, title, description, price, exist, image, sprCategory, sprColor, sprConsumer, sprSize, sprType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        return Objects.equals(this.idProduct, other.idProduct)
                && Objects.equals(this.productCode, other.productCode)
                && Objects.equals(this.title, other.title)
                && Objects.equals(this.description, other.description)
                && Objects.equals(this.price, other.price)
                && Objects.equals(this.exist, other.exist)
                && Objects.equals(this.image, other.image)
                && Objects.equals(this.sprCategory, other.sprCategory)
                && Objects.equals(this.sprColor, other.sprColor)
                && Objects.equals(this.sprConsumer, other.sprConsumer)
                && Objects.equals(this.sprSize, other.sprSize)
                && Objects.equals(this.sprType, other.sprType);
    }
}
