package fa.training.services;

import fa.training.entites.RoleName;
import fa.training.entites.Roles;

import java.util.Optional;

public interface RolesService {
    Optional<Roles> findByRoleName(RoleName roleName);
}
