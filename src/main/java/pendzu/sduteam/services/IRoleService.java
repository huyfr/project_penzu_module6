package pendzu.sduteam.services;

import pendzu.sduteam.models.Role;
import pendzu.sduteam.models.RoleName;

import java.util.Optional;

public interface IRoleService extends GenericService<Role>{
    Optional<Role> findByName(RoleName roleName);
}
