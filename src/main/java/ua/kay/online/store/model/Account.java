package ua.kay.online.store.model;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table()
public class Account extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4756418699652703026L;

    @Column(unique = true, nullable = false)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        byte[] encodedPassword = Base64.decodeBase64(password);
        return new String(encodedPassword);
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
