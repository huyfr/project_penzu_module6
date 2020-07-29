package pendzu.sduteam.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pendzu.sduteam.models.Role;
import pendzu.sduteam.models.User;
import pendzu.sduteam.repositories.IUserRepository;
import pendzu.sduteam.services.IUserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@PropertySource({"classpath:status.properties"})
public class UserServiceImpl implements IUserService {
    @Value("${user.deleted}")
    private int deleteUserStatus;

    @Value("${user.block}")
    private int blockUserStatus;

    @Value("${user.active}")
    private int activeUserStatus;

    @Autowired
    private IUserRepository repository;

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public Iterable<User> findUsersByNameContaining(String user_name) {
        return repository.findUsersByNameContaining(user_name);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        Optional<User> userOptional = repository.findById(id);
        User user = userOptional.get();

        user.setStatus(deleteUserStatus);
        repository.save(user);
    }

    @Override
    public void blockUser(Long id) {
        Optional<User> userOptional = repository.findById(id);
        User user = userOptional.get();

        user.setStatus(blockUserStatus);
        repository.save(user);
    }


    @Override
    public Page<User> findAllUserPagination(Pageable pageable, Set<Role> role) {
        return repository.findAllByRoles(pageable, role);
    }

    public void activeUser(Long id) {
        Optional<User> userOptional = repository.findById(id);
        User user = userOptional.get();

        user.setStatus(activeUserStatus);
        repository.save(user);
    }

    @Override
    public Page<User> findAllUserPagination(Pageable pageable) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Iterable<User> findAllByStatusAndCreateDateBetween(int status, LocalDateTime fromDate, LocalDateTime toDate) {
        return repository.findAllByStatusAndCreateDateBetween(status,fromDate,toDate);
    }
//
//    @Override
//    public List<Object[]> findAllUserViaQuery() {
//        return repository.findAllQuery();
//    }

    //    @Override
//    public Iterable<User> findAllByStatusAndCreateDate_MonthAndCreateDate_Date(int status, Month createDate_month, LocalDate createDate_date) {
//        return repository.findAllByStatusAndCreateDate_MonthAndCreateDate_Date(status,createDate_month,createDate_date);
//    }
}
