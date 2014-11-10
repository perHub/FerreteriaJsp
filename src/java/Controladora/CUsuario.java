/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;

import DAO.*;
import Exceptions.NoException;
import Modelo.*;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Peri
 */
@ManagedBean(name = "CUsuario")
@RequestScoped
public class CUsuario {

    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    DAOUsuario dUsr = new DAOUsuario();
//   DAOAdministrador dAdm = new DAOAdministrador();
//   DAOCliente dCl = new DAOCliente();

    public Usuario read(int id) {
        return dUsr.read(id);
    }

    public void agregar(Usuario u) {
        dUsr.create(u);
    }

    public void actualizar(Usuario u) {
        dUsr.update(u);
    }

    public List getAll() {
        return dUsr.getAll();
    }

    public String login() throws NoException {
//        return dUsr.login(username, password);
        String rtr = "";
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if ((usuario = (dUsr.login(usuario.getUsername(), usuario.getPassword()))) != null) {

            session.setAttribute("usuario", usuario);

            if (!usuario.isActivo()) {
                throw new NoException("Usuario inactivo.");
            }

            if (usuario.getClass() == Administrador.class) {
                rtr = "a";
            } else {
                session.setAttribute("compUsrId", usuario.getId());
                rtr = "clienteLogged";
            }

        }
        return rtr;
    }
    public void settearUsuarios(){
         List<Usuario> usuarios = dUsr.getAll();
         session.setAttribute("usuarios", usuarios);
         session.setAttribute("admin", "Administrador");
    }
    public Usuario getById(int id) {
        return dUsr.read(id);
    }

}
