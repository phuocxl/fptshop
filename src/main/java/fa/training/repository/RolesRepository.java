package fa.training.repository;

import fa.training.entity.RoleName;
import fa.training.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByRoleName(RoleName roleName);
}
