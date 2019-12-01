package ua.kay.monolith.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Cacheable
@Entity
@Table(name = "slider")
public class Slider extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 5254131315829584037L;

    @ManyToOne
    @JoinColumn(name = "image_id", nullable = false)
    @NotNull
    private Image image;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
