package ua.kay.online.store.model;

import org.hibernate.annotations.Type;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Cacheable
@Entity
@Table(name = "pages")
public class Page extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 2870463497495545664L;

    @Column(name = "url", unique = true, nullable = false)
    private String url;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "content", nullable = false)
    @Type(type = "text")
    private String content;

    public Page() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id = " + getId() + '\'' +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
