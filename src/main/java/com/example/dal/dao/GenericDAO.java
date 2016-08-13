package com.example.dal.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.LogicalExpression;

public interface GenericDAO<T, ID extends Serializable> {

    T get(ID id);

    T load(ID id);

    T findById(ID id, boolean lock);

    T findByID(ID id);

    List<T> findAll();

    List<T> findByExample(T exampleInstance, String... excludeProperty);

    List<T> findByCriteria(LogicalExpression logicalExpression);

    T saveOrUpdate(T entity);

    List<T> saveOrUpdateAll(List<T> list);

    T save(T entity);

    T save(T entity, boolean instant);

    List<T> saveAll(List<T> list);

    T update(T entity);
    
    List<T> updateAll(List<T> list);

    void deleteById(ID id);

    void delete(T entity);

    void deleteAll(List<T> list);

    /**
     * Affects every managed instance in the current persistence context!
     */
    void flush();

    /**
     * Affects every managed instance in the current persistence context!
     */
    void clear();
    
    T merge(T entity);
    
}
