package pendzu.sduteam.services;

import java.util.List;

public interface GenericService<T> {
    List<T> findAllExist();

    T findExistById(int id);

    T findDeletedById(int id);

    T save(T model);

    boolean delete(int id);
}
