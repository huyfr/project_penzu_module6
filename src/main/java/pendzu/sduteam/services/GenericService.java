package pendzu.sduteam.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GenericService<T> {
    Optional<T> findById(Long id);

    Iterable<T> findAll();

    T save(T model);

    void delete(Long id);


}
