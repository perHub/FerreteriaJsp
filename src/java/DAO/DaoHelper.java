/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javax.persistence.*;

/**
 *
 * @author Peri
 */
public class DaoHelper {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    static {
        if(emf == null)
            emf = Persistence.createEntityManagerFactory("FerreteriaServletsPU");
        
        if(em == null)
            em = emf.createEntityManager();

    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }

//    public void setEmf(EntityManagerFactory emf) {
//        this.emf = emf;
//    }
    public static EntityManager getEm() {
        return em;
    }

//    public void setEm(EntityManager em) {
//        this.em = em;
//    }
}
