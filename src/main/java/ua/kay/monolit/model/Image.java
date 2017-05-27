package ua.kay.monolit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Cacheable
@Component
@Scope("prototype")
@Entity
@Table(name = "image", catalog = "monolit")
public class Image implements Serializable {

    private static final long serialVersionUID = 8794364830985023953L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_image", nullable = false)
    private long idImage;

    @Column(name = "image", nullable = false)
    @NotNull
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
}
