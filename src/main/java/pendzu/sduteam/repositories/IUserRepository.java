package pendzu.sduteam.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pendzu.sduteam.models.User;

import java.util.Optional;

public interface IUserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    Iterable<User> findUsersByNameContaining(String user_name);

    @Query("select u from User u where u.status >= 0")
    Page<User> findAllByStatusAndStatus(Pageable pageable);
}
