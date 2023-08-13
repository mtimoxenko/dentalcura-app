package org.clinic.persistence;

import java.util.List;

public interface IDao<T> {

    void createTable();
    T insert(T t);
    List<T> selectAll();
    T selectByID(Long id);
    T updateByID(T t);
    T deleteByID(Long id);

}
