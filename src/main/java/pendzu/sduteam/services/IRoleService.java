package pendzu.sduteam.services;

import pendzu.sduteam.models.Role;
import pendzu.sduteam.models.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName roleName);
}
