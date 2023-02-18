package fa.training.entites;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRole;
    @Column
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public Roles() {
    }

    public Roles(long idRole, RoleName roleName) {
        this.idRole = idRole;
        this.roleName = roleName;
    }

    public long getIdRole() {
        return idRole;
    }

    public void setIdRole(long idRole) {
        this.idRole = idRole;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
