package fa.training.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "userName"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;
    @Column
    @NotBlank
    private String fullName;
    @Column
    @NotBlank
    @Size(min = 3, max =100)
    private String userName;
    @NotBlank
    @Size(min = 6, max =100)
    @JsonIgnore
    private String password;
    @Column
    @Email
    @NotBlank
    private String email;
    @Column
    private LocalDate date;
    @Column
    private String phone;
    @Column
    private LocalDate birthday;
    @Column
    private boolean softDelete;
    @ManyToMany
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "id_user"),inverseJoinColumns = @JoinColumn(name = "id_role"))
    Set<Roles> roles = new HashSet<>();

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    private Set<Order> orders;

    public Users() {
    }

    public Users(long idUser, String fullName, String userName, String password, String email, LocalDate date,
                 String phone, LocalDate birthday, boolean softDelete, Set<Roles> roles) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.date = date;
        this.phone = phone;
        this.birthday = birthday;
        this.softDelete = softDelete;
        this.roles = roles;
    }

    public Users( @NotBlank @Size(min = 3, max =100) String userName,
                  @NotBlank @Size(min = 6, max =100) String encode,
                  @Email @NotBlank String email,
                  @NotBlank String name) {
        this.userName = userName;
        this.password = encode;
        this.email = email;
        this.fullName = name;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public boolean isSoftDelete() {
        return softDelete;
    }

    public void setSoftDelete(boolean softDelete) {
        this.softDelete = softDelete;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
