package fa.training.security.userprincal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fa.training.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {

    private Long idUser;
    private String fuillName;
    private String userName;
    @JsonIgnore
    private String password;
    private String email;
    private LocalDate date;
    private String phone;
    private LocalDate birthday;
    private boolean softDelete;
    private Collection<? extends  GrantedAuthority> roles;

    public UserPrinciple() {
    }

    public UserPrinciple(Long idUser, String fuillName, String userName, String password, String email, LocalDate date,
                         String phone, LocalDate birthday,
                         Collection<? extends GrantedAuthority> roles) {
        this.idUser = idUser;
        this.fuillName = fuillName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.date = date;
        this.phone = phone;
        this.birthday = birthday;
        this.roles = roles;
    }

    public UserPrinciple(Long idUser, String fuillName, String userName, String password, String email, LocalDate date,
                         String phone, LocalDate birthday, boolean softDelete,
                         Collection<? extends GrantedAuthority> roles) {
        this.idUser = idUser;
        this.fuillName = fuillName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.date = date;
        this.phone = phone;
        this.birthday = birthday;
        this.softDelete = softDelete;
        this.roles = roles;
    }

    public String getFuillName() {
        return fuillName;
    }

    public void setFuillName(String fuillName) {
        this.fuillName = fuillName;
    }

    public static UserPrinciple build(Users user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());
        return new UserPrinciple(
                user.getIdUser(),
                user.getFullName(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getDate(),
                user.getPhone(),
                user.getBirthday(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
