package ua.kay.monolit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

@Component
@Scope("prototype")
@Entity
@Table(name = "image", schema = "", catalog = "monolit")
public class Image implements Serializable {

    private static final long serialVersionUID = 8794364830985023953L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_image")
    private long idImage;

    @Basic
    @Column(name = "image")
    private byte[] image;

    @JsonIgnore
    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private Set<SprCategory> sprCategories;

    @JsonIgnore
    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private Set<Product> product;

    public Set<SprCategory> getSprCategories() {
        return sprCategories;
    }

    public void setSprCategories(Set<SprCategory> sprCategories) {
        this.sprCategories = sprCategories;
    }

    public long getIdImage() {
        return idImage;
    }

    public void setIdImage(long idImage) {
        this.idImage = idImage;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image1 = (Image) o;

        if (idImage != image1.idImage) return false;
        if (!Arrays.equals(image, image1.image)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idImage ^ (idImage >>> 32));
        result = 31 * result + (image != null ? Arrays.hashCode(image) : 0);
        return result;
    }

}
