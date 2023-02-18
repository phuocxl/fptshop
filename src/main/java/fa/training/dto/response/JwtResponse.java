package fa.training.dto.response;
import java.util.List;

public class JwtResponse {
    private long id;
    private String token;
    private String type = "Bearer";

    private long idUser;
    private String name;
    private List<String> roles;

    public JwtResponse() {
    }

    public JwtResponse(long id, String token, String type, long idUser, String name, List<String> roles) {
        this.id = id;
        this.token = token;
        this.type = type;
        this.idUser = idUser;
        this.name = name;
        this.roles = roles;
    }

    public JwtResponse(String token, long idUser, String getFuillName, List<String> roles) {
        this.token = token;
        this.idUser = idUser;
        this.name = getFuillName;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
