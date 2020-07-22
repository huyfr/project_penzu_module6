package pendzu.sduteam.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import pendzu.sduteam.models.User;
import pendzu.sduteam.repositories.IUserRepository;
import pendzu.sduteam.services.IUserService;

import java.util.Optional;

@Service
@PropertySource({"classpath:status.properties"})
public class UserServiceImpl implements IUserService {
    @Value("${user.deleted}")
    private int deleteUserStatus;

    @Value("${user.block}")
    private int blockUserStatus;

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
}
