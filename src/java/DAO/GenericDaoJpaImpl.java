/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Peri
 * @param <T>
 * @param <PK>
 */
public abstract class GenericDaoJpaImpl<T, PK extends Serializable>
        implements GenericDao<T, PK> {

    protected Class<T> entityClass;

    @PersistenceContext
    protected EntityManager em = DaoHelper.getEm();
    protected EntityManagerFactory emf = DaoHelper.getEmf();

//    {
//        emf = Persistence.createEntityManagerFactory("FerreteriaServletsPU");
//        em = emf.createEntityManager();
//    }
    public GenericDaoJpaImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
                .getActualTypeArguments()[0];
    }

    @Override
    public T create(T t) throws ConstraintViolationException {
        Session session = em.unwrap(Session.class);

        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } catch (ConstraintViolationException ex) {
            throw ex;
        } finally {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }
        session.refresh(t);
        return t;
    }

    @Override
    public T read(PK id) {
        return this.em.find(entityClass, id);
    }

    @Override
    public T update(T t) {
        try {
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
        } finally {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }
        return t;
    }

    @Override
    public void delete(T t) {
        try {
            em.getTransaction().begin();
            t = this.em.merge(t);
            this.em.remove(t);
            em.getTransaction().commit();
        } finally {
           if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        }
    }

    public List getAll() {
        Session session = em.unwrap(Session.class);
        return session.createCriteria(entityClass)
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .list();
    }

    public Map getAllMap() {
        List<T> lst = getAll();
        HashMap<PK, T> all = new HashMap<>();

        for (T obj : lst) {
            Session session = em.unwrap(Session.class);
            PK id = (PK) session.getIdentifier(obj);
            all.put(id, (T) obj);

        }
        return all;
    }

}
