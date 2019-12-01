package ua.kay.monolith.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "image")
public class Image extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8794364830985023953L;

    @Column(name = "image", nullable = false)
    @NotNull
    private byte[] image;

    @JsonIgnore
    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private Set<Category> categories;

    @JsonIgnore
    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private Set<Product> products;

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
