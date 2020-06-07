package ua.kay.online.store.service;

import ua.kay.online.store.exception.ObjectNotFoundException;

import java.util.List;

public interface CrudService<T> {
    List<T> findAll();

    T findById(Long id) throws ObjectNotFoundException;

    T save(T t);

    void delete(T t);

    void deleteById(Long id);
}
