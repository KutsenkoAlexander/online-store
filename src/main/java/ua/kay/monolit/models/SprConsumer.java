package ua.kay.monolit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "spr_consumer", schema = "", catalog = "monolit")
public class SprConsumer implements Serializable {

    private static final long serialVersionUID = 7004225799224057164L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_consumer", nullable = false, insertable = true, updatable = true)
    private long idConsumer;

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 255)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "sprConsumerByConsumerId")
    private Collection<Product> productsByIdConsumer;

    public long getIdConsumer() {
        return idConsumer;
    }

    public void setIdConsumer(long idConsumer) {
        this.idConsumer = idConsumer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Product> getProductsByIdConsumer() {
        return productsByIdConsumer;
    }

    public void setProductsByIdConsumer(Collection<Product> productsByIdConsumer) {
        this.productsByIdConsumer = productsByIdConsumer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SprConsumer that = (SprConsumer) o;

        if (idConsumer != that.idConsumer) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (idConsumer ^ (idConsumer >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

}
