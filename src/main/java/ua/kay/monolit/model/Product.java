package ua.kay.monolit.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Cacheable
@Entity
@Table(name = "product", catalog = "monolit")
public class Product implements Serializable {

    private static final long serialVersionUID = -7651298149649100262L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_product", nullable = false)
    private long idProduct;

    @Column(name = "product_code", unique = true, nullable = false)
    @NotNull
    private String productCode;

    @Column(name = "title", nullable = false)
    @NotNull
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull
    private double price;

    @Column(name = "exist")
    private byte exist;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private SprCategory sprCategory;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private SprColor sprColor;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private SprConsumer sprConsumer;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private SprSize sprSize;

    @ManyToOne
    @JoinColumn(name = "type_id")
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

}
