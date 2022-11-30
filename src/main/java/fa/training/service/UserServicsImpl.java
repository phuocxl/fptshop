package fa.training.service;

import fa.training.entity.Users;
import fa.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServicsImpl implements UserServics{

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
