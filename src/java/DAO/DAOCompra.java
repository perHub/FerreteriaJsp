/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Compra;
import Modelo.Detalle;
import java.util.List;
import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Peri
 */
public class DAOCompra extends GenericDaoJpaImpl<Compra, Integer> {

//    @Override
//    public List<Compra> getAll() {
//        Session session = em.unwrap(Session.class);
//        return session.createCriteria(entityClass)
//                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
//                .list();
//    }
    public List<Compra> getPendientes() {
        Session session = em.unwrap(Session.class);
        return session.createCriteria(entityClass)
                .add(Restrictions.eq("procesado", false))
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .addOrder(Order.desc("fecha"))
                .list();
    }

    public List<Compra> getProcesados() {
        Session session = em.unwrap(Session.class);
        return session.createCriteria(entityClass)
                .setCacheMode(CacheMode.IGNORE)
                .add(Restrictions.eq("procesado", true))
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .addOrder(Order.desc("fecha"))
                .list();
    }

    public List<Compra> getAllbyUsrId(int id) {
        return em.createNativeQuery("select c.id,c.fecha, c.total from ferreteria.compras c JOIN usuarios_compras uc ON c.id=uc.compras_id WHERE uc.usuarios_id=" + id, entityClass)
                .getResultList();
    }

    public List<Detalle> getDetalles(int id) {
        return em.createNativeQuery("SELECT d.id, d.cantidad, d.precio FROM detalles d JOIN compras_detalles cd ON d.id=cd.detalles_id WHERE cd.compras_id=" + id, Detalle.class)
                .getResultList();
    }

}
