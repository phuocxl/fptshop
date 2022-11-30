package fa.training.service;

import fa.training.entity.RoleName;
import fa.training.entity.Roles;
import fa.training.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesRepository rolesRepository;
    @Override
    public Optional<Roles> findByRoleName(RoleName roleName) {
        return rolesRepository.findByRoleName(roleName);
    }
}
