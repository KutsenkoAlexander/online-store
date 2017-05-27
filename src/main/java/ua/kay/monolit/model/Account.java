package ua.kay.monolit.model;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Cacheable
@Entity
@Table(name = "account", catalog = "monolit")
public class Account implements Serializable {

    private static final long serialVersionUID = -4756418699652703026L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_account", nullable = false)
    private Long idAccount;

    @Column(name = "name", unique = true, nullable = false)
    @NotNull
    private String name;

    @Column(name = "password", nullable = false)
    @NotNull
    private String password;

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

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
