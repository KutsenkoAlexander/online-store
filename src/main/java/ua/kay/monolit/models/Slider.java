package ua.kay.monolit.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "slider", schema = "", catalog = "monolit")
public class Slider implements Serializable {

    private static final long serialVersionUID = 5254131315829584037L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_slider", nullable = false, insertable = true, updatable = true)
    private Integer idSlider;

    @Basic
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "image_id", nullable = true)
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSlider, text, image);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Slider other = (Slider) obj;
        return Objects.equals(this.idSlider, other.idSlider)
                && Objects.equals(this.text, other.text)
                && Objects.equals(this.image, other.image);
    }

}
