package fa.training.service;

import fa.training.entity.Users;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserServics {
    Optional<Users> findByUserName(String userName);
    public Users addUser(Users user);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);
}
