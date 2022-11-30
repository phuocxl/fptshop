package fa.training.service;

import fa.training.entity.RoleName;
import fa.training.entity.Roles;

import java.util.Optional;

public interface RolesService {
    Optional<Roles> findByRoleName(RoleName roleName);
}
