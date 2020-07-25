package pendzu.sduteam.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pendzu.sduteam.models.User;

import java.util.Optional;

public interface IUserService extends GenericService<User> {
    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    Iterable<User> findUsersByNameContaining(String user_name);

    void blockUser(Long id);
    void activeUser(Long id);

    Page<User> findAllUserPagination(Pageable pageable);
}
