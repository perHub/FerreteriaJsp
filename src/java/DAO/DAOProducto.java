/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Producto;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Peri
 */
public class DAOProducto extends GenericDaoJpaImpl<Producto, Integer> {

    public List getAllActivated() {
        Session session = em.unwrap(Session.class);
        return session.createCriteria(entityClass)
                .add(Restrictions.eq("activo", true))
                 .list();
    }

}
