package ua.kay.online.store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Cacheable
@Entity
@Table
public class Image extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8794364830985023953L;

    @NotNull
    @Column(nullable = false, unique = true)
    private String url;

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

    public String getImage() {
        return url;
    }

    public void setImage(String url) {
        this.url = url;
    }
}
