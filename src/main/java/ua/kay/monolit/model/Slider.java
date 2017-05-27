package ua.kay.monolit.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Cacheable
@Entity
@Table(name = "slider", catalog = "monolit")
public class Slider implements Serializable {

    private static final long serialVersionUID = 5254131315829584037L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_slider", nullable = false)
    private Integer idSlider;

    @ManyToOne
    @JoinColumn(name = "image_id", nullable = false)
    @NotNull
    private Image image;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getIdSlider() {
        return idSlider;
    }

    public void setIdSlider(Integer idSlider) {
        this.idSlider = idSlider;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
