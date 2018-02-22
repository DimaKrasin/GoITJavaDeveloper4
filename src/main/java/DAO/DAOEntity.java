package DAO;

import java.util.List;

public interface DAOEntity <T, ID, N> {
    T getById(ID id);

    List<T> getAll();

    List<T> getByName(N name);

    void create(T t);

    void update(T t);

    void delete(T t);
}
