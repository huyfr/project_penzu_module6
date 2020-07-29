package pendzu.sduteam.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import pendzu.sduteam.models.Role;
import pendzu.sduteam.models.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IUserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    Iterable<User> findUsersByNameContaining(String user_name);

    @Query("select u from User u where u.status >= 0")
    Page<User> findAllByRoles(Pageable pageable, Set<Role> role);

    User findByEmail(@NotBlank @Email @Size(max = 64) String email);

    Iterable<User> findAllByStatusAndCreateDateBetween(int status, LocalDateTime fromDate, LocalDateTime toDate);

//    @Query("Select user.createDate, count(user) from User user group by function('date', user.createDate)")
//    List<Object[]> findAllQuery();

//    Iterable<User> findAllByStatusAndCreateDate_MonthAndCreateDate_Date(int status, Month createDate_month, LocalDate createDate_date);
}
