package fa.training.services;

import fa.training.entites.Users;

import java.util.Optional;

public interface UserServics {
    Optional<Users> findByUserName(String userName);
    public Users addUser(Users user);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);
}
