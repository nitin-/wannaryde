package com.example.dal.dao;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.LogicalExpression;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;


@Repository
@SuppressWarnings(value = "unchecked")
public abstract class GenericHibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {
    private Class<T> persistentClass;
    protected SessionFactory sessionFactory;

    public GenericHibernateDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        if (sessionFactory == null)
            throw new IllegalStateException("SessionFactory has not been set on DAO before usage");
        return sessionFactory.getCurrentSession();
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    public T get(ID id) {
        return findByID(id);
    }

    @Override
    public T load(ID id) {
        return (T) getSession().load(getPersistentClass(), id);
    }

    @Override
    public T findById(ID id, boolean lock) {
        T entity;
        if (lock)
            entity = (T) getSession().get(getPersistentClass(), id, LockOptions.READ);
        else
            entity = (T) getSession().get(getPersistentClass(), id);

        return entity;
    }

    @Override
    public T findByID(ID id) {
        return (T) getSession().get(getPersistentClass(), id);
    }

    @Override
    public List<T> findAll() {
        return findByCriteria();
    }

    @Override
    public List<T> findByExample(T exampleInstance, String... excludeProperty) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        Example example = Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }

    @Override
    public List<T> findByCriteria(LogicalExpression logicalExpression) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(logicalExpression);
        List<T> results = criteria.list();
        return results;
    }

    @Override
    public T saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    public List<T> saveOrUpdateAll(List<T> list) {
        if(list == null || list.size() == 0) return null;
        for (T entity : list) {
            saveOrUpdate(entity);
        }
        return list;
    }

    @Override
    public T save(T entity) {
        getSession().save(entity);
        return entity;
    }

    @Override
    public T save(T entity, boolean instant) {
        if(!instant) return save(entity);
        Session session = getSession();
        //Transaction transaction = session.beginTransaction();
        getSession().setFlushMode(FlushMode.COMMIT);
        session.save(entity);
        session.flush();
        //transaction.commit();
        return entity;
    }

    @Override
    public List<T> saveAll(List<T> list) {
        if(list == null || list.size() == 0) return null;
        for (T entity : list) {
            save(entity);
        }
        return list;
    }

    @Override
    public T update(T entity) {
        getSession().update(entity);
        return entity;
    }

    @Override
    public List<T> updateAll(List<T> list) {
        if(list == null || list.size() == 0) return null;
        for (T entity : list) {
        	update(entity);
        }
        return list;
    }
    
    @Override
    public void deleteById(ID id) {
        delete(findByID(id));
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public void deleteAll(List<T> list) {
        if(list == null || list.size() == 0) return;
        for (T entity : list) {
            delete(entity);
        }
    }

    @Override
    public void flush() {
        getSession().flush();
    }

    @Override
    public void clear() {
        getSession().clear();
    }

    protected DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass(getPersistentClass());
    }

    protected Criteria createCriteria() {
        return getSession().createCriteria(getPersistentClass());
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    protected List<T> findByCriteria(Criterion... criterion) {
        Criteria crit = createCriteria();
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }

    protected T findUniqueByCriteria(Criterion... criterion) {
        List<T> l = findByCriteria(criterion);
        if (l != null && l.size() > 0)
            return l.get(0);

        return null;
    }

    protected List<T> findByQuery(final String queryString, final Object... values) {
        Query query = getSession().createQuery(queryString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query.list();
    }

    protected List<T> findByNamedParam(final String queryString, final Map<String, Object> map) {
        Query query = getSession().createQuery(queryString);
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String paramName = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof Collection) {
                    query.setParameterList(paramName, (Collection<?>) value);
                } else if (value instanceof Object[]) {
                    query.setParameterList(paramName, (Object[]) value);
                } else {
                    query.setParameter(paramName, value);
                }
            }
        }
        return query.list();
    }

    protected T findUniqueByQuery(final String queryString, final Object... values) {
        List<T> list = findByQuery(queryString, values);
        if (!CollectionUtils.isEmpty(list))
            return list.get(0);
        return null;
    }

    protected T findUniqueByNamedParam(final String queryString, final Map<String, Object> map) {
        List<T> list = findByNamedParam(queryString, map);
        if (!CollectionUtils.isEmpty(list))
            return list.get(0);
        return null;
    }

    protected Query createQuery(String queryString) {
        Query query = getSession().createQuery(queryString);
        return query;
    }
    
    @Override
    public T merge(T entity){
    	getSession().merge(entity);
    	return entity;
    }

}

