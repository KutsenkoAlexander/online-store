package ua.kay.monolit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users", schema = "", catalog = "monolit")
public class Users implements Serializable{

    private static final long serialVersionUID = -3973131338862519382L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_users", nullable = false, insertable = true, updatable = true)
    private int idUsers;

    @Basic
    @Column(name = "username", nullable = false, insertable = true, updatable = true, length = 45)
    private String username;

    @Basic
    @Column(name = "password", nullable = false, insertable = true, updatable = true, length = 45)
    private String password;

    @Basic
    @Column(name = "enabled", nullable = false, insertable = true, updatable = true, length = 4)
    private int enabled;

    @JsonIgnore
    @OneToMany(mappedBy = "usersId", fetch = FetchType.LAZY)
    private Set<UsersRoles> usersRoles;

    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Set<UsersRoles> getUsersRoles() {
        return usersRoles;
    }

    public void setUsersRoles(Set<UsersRoles> usersRoles) {
        this.usersRoles = usersRoles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsers, username, password, enabled, usersRoles);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Users other = (Users) obj;
        return Objects.equals(this.idUsers, other.idUsers)
                && Objects.equals(this.username, other.username)
                && Objects.equals(this.password, other.password)
                && Objects.equals(this.enabled, other.enabled)
                && Objects.equals(this.usersRoles, other.usersRoles);
    }
}
