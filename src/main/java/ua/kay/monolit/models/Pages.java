package ua.kay.monolit.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "pages", schema = "", catalog = "monolit")
public class Pages implements Serializable {

    private static final long serialVersionUID = 2870463497495545664L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private long id;

    @Basic
    @Column(name = "url", nullable = true, insertable = true, updatable = true, length = 255)
    private String url;

    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 255)
    private String description;

    @Basic
    @NotNull
    @Column(name = "content", nullable = false, insertable = true, updatable = true, length = 65535)
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pages pages = (Pages) o;

        if (id != pages.id) return false;
        if (url != null ? !url.equals(pages.url) : pages.url != null) return false;
        if (description != null ? !description.equals(pages.description) : pages.description != null) return false;
        return content != null ? content.equals(pages.content) : pages.content == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

}
