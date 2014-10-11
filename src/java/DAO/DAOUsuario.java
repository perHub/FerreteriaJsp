/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.*;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Peri
 */
public class DAOUsuario extends GenericDaoJpaImpl<Usuario, Integer> {

    @Override
    public Usuario read(Integer id) {
        Usuario usr;

        if ((usr = em.find(Administrador.class, id)) != null) {
            return usr;
        } else {
            return em.find(Cliente.class, id);
        }
    }

    public Usuario login(String username, String password) {
        Session session = em.unwrap(Session.class);
        List<Usuario> lstUsr = session.createCriteria(entityClass)
                .add(Restrictions.eq("username", username))
                .add(Restrictions.eq("password", password))
                .list();
        
        if(lstUsr.size() > 0)
            return lstUsr.get(0);
        else
            return null;
    }
    
    @Override
    public List getAll(){
        List cls = em.createQuery("select c from Cliente as c", Cliente.class).getResultList();
        List all = em.createQuery("select a from Administrador as a", Administrador.class).getResultList();
        all.addAll(cls);
        
        return all;
    }

}
