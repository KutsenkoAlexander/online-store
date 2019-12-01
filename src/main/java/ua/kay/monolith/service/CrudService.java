package ua.kay.monolith.service;

import ua.kay.monolith.exceptions.ObjectNotFoundException;

import java.util.stream.Stream;

public interface CrudService<T> {
    Stream<T> findAll();

    T findById(Long id) throws ObjectNotFoundException;

    T save(T t);

    void delete(T t);

    void deleteById(Long id);
}
