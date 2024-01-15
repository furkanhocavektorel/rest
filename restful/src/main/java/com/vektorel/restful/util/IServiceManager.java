package com.vektorel.restful.util;

import java.util.List;
import java.util.Optional;

public interface IServiceManager<T,ID> {

    void save(T t);
    void deleteById(ID id);
    void delete(T t);
    void update(T t);
    Optional<T> findById(ID id);
    List<T> getAll();


}
