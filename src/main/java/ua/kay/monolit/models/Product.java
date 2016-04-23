package ua.kay.monolit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "Product", schema = "", catalog = "monolit")
public class Product implements Serializable {

    private static final long serialVersionUID = -7651298149649100262L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_product", nullable = false, insertable = true, updatable = true)
    private long idProduct;

    @Basic
    @Column(name = "product_code", nullable = false, insertable = true, updatable = true)
    private Long productCode;

    @Basic
    @Column(name = "category_id", nullable = false, insertable = true, updatable = true)
    private Integer categoryId;

    @Basic
    @Column(name = "title", nullable = false, insertable = true, updatable = true, length = 255)
    private String title;

    @Basic
    @Column(name = "description", nullable = false, insertable = true, updatable = true, length = 2147483647)
    private String description;

    @Basic
    @Column(name = "price", nullable = false, insertable = true, updatable = true, precision = 0)
    private Double price;

    @Basic
    @Column(name = "exist", nullable = false, insertable = true, updatable = true)
    private Byte exist;

    @Basic
    @Column(name = "consumer_id", nullable = false, insertable = true, updatable = true)
    private Long consumerId;

    @Basic
    @Column(name = "color_id", nullable = false, insertable = true, updatable = true)
    private Integer colorId;

    @Basic
    @Column(name = "type_id", nullable = false, insertable = true, updatable = true)
    private Integer typeId;

    @Basic
    @Column(name = "size_id", nullable = false, insertable = true, updatable = true)
    private Integer sizeId;

    @JsonIgnore
    @OneToMany(mappedBy = "productByProductId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ImgProduct> imgProductsByIdProduct;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id_category", nullable = false, insertable = false, updatable = false)
    private SprCategory sprCategoryByCategoryId;

    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "id_spr_colors", nullable = false, insertable = false, updatable = false)
    private SprColor sprColorByColorId;

    @ManyToOne
    @JoinColumn(name = "consumer_id", referencedColumnName = "id_consumer", nullable = false, insertable = false, updatable = false)
    private SprConsumer sprConsumerByConsumerId;

    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "id_spr_size", nullable = false, insertable = false, updatable = false)
    private SprSize sprSizeBySizeId;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id_spr_type", nullable = false, insertable = false, updatable = false)
    private SprType sprTypeByTypeId;

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Byte getExist() {
        return exist;
    }

    public void setExist(Byte exist) {
        this.exist = exist;
    }

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public Collection<ImgProduct> getImgProductsByIdProduct() {
        return imgProductsByIdProduct;
    }

    public void setImgProductsByIdProduct(Collection<ImgProduct> imgProductsByIdProduct) {
        this.imgProductsByIdProduct = imgProductsByIdProduct;
    }

    public SprCategory getSprCategoryByCategoryId() {
        return sprCategoryByCategoryId;
    }

    public void setSprCategoryByCategoryId(SprCategory sprCategoryByCategoryId) {
        this.sprCategoryByCategoryId = sprCategoryByCategoryId;
    }

    public SprColor getSprColorByColorId() {
        return sprColorByColorId;
    }

    public void setSprColorByColorId(SprColor sprColorByColorId) {
        this.sprColorByColorId = sprColorByColorId;
    }

    public SprConsumer getSprConsumerByConsumerId() {
        return sprConsumerByConsumerId;
    }

    public void setSprConsumerByConsumerId(SprConsumer sprConsumerByConsumerId) {
        this.sprConsumerByConsumerId = sprConsumerByConsumerId;
    }

    public SprSize getSprSizeBySizeId() {
        return sprSizeBySizeId;
    }

    public void setSprSizeBySizeId(SprSize sprSizeBySizeId) {
        this.sprSizeBySizeId = sprSizeBySizeId;
    }

    public SprType getSprTypeByTypeId() {
        return sprTypeByTypeId;
    }

    public void setSprTypeByTypeId(SprType sprTypeByTypeId) {
        this.sprTypeByTypeId = sprTypeByTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (idProduct != product.idProduct) return false;
        if (productCode != product.productCode) return false;
        if (categoryId != product.categoryId) return false;
        if (Double.compare(product.price, price) != 0) return false;
        if (exist != product.exist) return false;
        if (consumerId != product.consumerId) return false;
        if (colorId != product.colorId) return false;
        if (typeId != product.typeId) return false;
        if (sizeId != product.sizeId) return false;
        if (title != null ? !title.equals(product.title) : product.title != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (idProduct ^ (idProduct >>> 32));
        result = 31 * result + (int) (productCode ^ (productCode >>> 32));
        result = 31 * result + categoryId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) exist;
        result = 31 * result + (int) (consumerId ^ (consumerId >>> 32));
        result = 31 * result + colorId;
        result = 31 * result + typeId;
        result = 31 * result + sizeId;
        return result;
    }

}
