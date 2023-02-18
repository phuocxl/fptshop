package fa.training.services.impl;

import fa.training.entites.RoleName;
import fa.training.entites.Roles;
import fa.training.repositories.RolesRepository;
import fa.training.services.RolesService;
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
