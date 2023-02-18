package fa.training.services.impl;

import fa.training.entites.Users;
import fa.training.repositories.UserRepository;
import fa.training.services.UserServics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServicsImpl implements UserServics {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Optional<Users> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Users addUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
