package org.ccomp.data.database.dao;

import java.util.List;

public interface IDAO<T, K> {

   // void insert(T... objects);
    //void update(T... objects);
    void delete(T... objects);
    List<T> getAll();
    void deleteAll();
    T get(K... keys);
    void save(T...objects);
    List<K> getKeys();
}
