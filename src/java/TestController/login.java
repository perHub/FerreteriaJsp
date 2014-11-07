/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestController;

import Modelo.Administrador;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Peri
 */
@ManagedBean
@RequestScoped
public class login {

    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

    public void login() {
        Object o = session.getAttribute("usuario");
        session.setAttribute("usuario", new Administrador());
        session.getId();
    }
}
