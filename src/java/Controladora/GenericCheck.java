/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;

import Modelo.*;
import java.io.IOException;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dev
 */
@ManagedBean(name = "GenericCheck")
@RequestScoped
public class GenericCheck {

    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

    public boolean emptyParams() throws Exception {
        if (requestParams.isEmpty()) {
            throw new Exception("Imposible realizar la operación solicitada.");
        }

        return true;
    }

    public void loginRedirect() throws IOException {

        Usuario usr = (Usuario) session.getAttribute("usuario");
        if (usr != null) {
            if (usr.getClass().equals(Administrador.class)) {
                fc.getExternalContext().redirect("admin_usuarios.xhtml");
                fc.responseComplete();
            } else if (usr.getClass().equals(Cliente.class)) {
                fc.getExternalContext().redirect("main.xhtml");
                fc.responseComplete();
            }
        }
    }
}
