package fa.training.dto;

import java.time.LocalDate;
import java.util.Set;

public class UserDTO {
    private String name;
    private String userName;
    private String email;
    private String password;
    private LocalDate date;
    private Set<String> roles;

    public UserDTO() {
    }

    public UserDTO(String name, String userName, String email, String password, LocalDate date, Set<String> roles) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.date = date;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
