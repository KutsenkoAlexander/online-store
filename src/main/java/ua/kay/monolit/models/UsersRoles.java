package ua.kay.monolit.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_roles", schema = "",  catalog = "monolit")
public class UsersRoles implements Serializable {

    private static final long serialVersionUID = 7884756336272189839L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_user_roles", nullable = false, insertable = true, updatable = true)
    private int idUserRoles;

    @Basic
    @Column(name = "role", nullable = false, insertable = true, updatable = true, length = 45)
    private String role;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private Users usersId;

    public int getIdUserRoles() {
        return idUserRoles;
    }

    public void setIdUserRoles(int idUserRoles) {
        this.idUserRoles = idUserRoles;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Users getUsersId() {
        return usersId;
    }

    public void setUsersId(Users usersId) {
        this.usersId = usersId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUserRoles, role, usersId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final UsersRoles other = (UsersRoles) obj;
        return Objects.equals(this.idUserRoles, other.idUserRoles)
                && Objects.equals(this.role, other.role)
                && Objects.equals(this.usersId, other.usersId);
    }
}
