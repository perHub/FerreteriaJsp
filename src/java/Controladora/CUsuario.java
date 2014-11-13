/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;

import DAO.*;
import Exceptions.AdminClienteException;
import Exceptions.NoException;
import Exceptions.loginException;
import Modelo.*;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Peri
 */
@ManagedBean(name = "CUsuario")
@RequestScoped
public class CUsuario {

    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

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

    public Usuario getById(int id) {
        return dUsr.read(id);
    }

    //Los métodos de arriba deberán ser eliminados enventualmente.
//**********************************************************
//Los siguientes métodos ayudan a las relgas de navegación:
//**********************************************************
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
                rtr = "AdminUsr";
            } else {
                session.setAttribute("compUsrId", usuario.getId());
                rtr = "ClienteLoggedIn";
            }

        }
        return rtr;
    }
    
    public void clearUsr0()
    {
        if(requestParams.isEmpty()){
            Cliente cl = new Cliente();
            cl.setActivo(true);
            session.setAttribute("usr0", cl);
        }
    }

    public String cargarUsuarioParaEdicion() throws Exception {

        Usuario usr;
        if (!requestParams.isEmpty()) {
            int usrId = Integer.parseInt(requestParams.get("usrId"));
            usr = dUsr.read(usrId);

            session.setAttribute("usr0", usr);

            return "EditUsr";
        }
        return "Error";
    }

    public String saveUsr() { //El scope es session por facilidad
        
        Cliente usr = (Cliente) session.getAttribute("usr0");

        dUsr.update(usr);

        return "AdminUsr";
    }

//************************************************************************************
//Los siguientes métodos realizan tareas no relacionadas con las reglas de navegacion:
//************************************************************************************
    public Boolean usuarioEstaLogueado() throws loginException {
        Usuario usr = (Usuario) session.getAttribute("usuario");

        if (usr != null) {
            if (usr.getClass().equals(Administrador.class) || usr.getClass().equals(Cliente.class)) {
                return true;
            }
        }
        throw new loginException();     //Controlo el estado de "no estar logueado" con una excepción para poder capturarla fácilmente en la página de error.
    }

    //El siguiente método sólo deberá ser usado con el objetivo de que el framework pueda atrapar determinadas excepciones y responder en consecuencia. Pero NO debe usarse como comprobación lógica.
    public Boolean soloVisibleParaAdmin(Boolean CA) throws loginException, AdminClienteException { //True=Página sólo podrá ser vista por el admin; False= Sólo por el Cliente
        Usuario usr = (Usuario) session.getAttribute("usuario");

        Boolean esAdm = usr.getClass().equals(Administrador.class);
        Boolean esCliente = usr.getClass().equals(Cliente.class);

        if (!esAdm && !esCliente) {             //Si ninguna de estas comprobaciones se cumple, la página podrá ser renderizada
            throw new loginException();
        }
        if (!CA && esAdm) {
            throw new AdminClienteException();
        } else if (CA && esCliente) {
            throw new AdminClienteException();
        }

        return true;
    }

//    public Boolean usuarioLogueadoEsAdmin() throws loginException {
//        Usuario usr = (Usuario) session.getAttribute("usuario");
//
//        if (usr.getClass().equals(Administrador.class)) {
//            return true;
//        } else if (usr.getClass().equals(Cliente.class)) {
//            return false;
//        }
//        throw new loginException();
//    }
    public void settearUsuarios() { //Actualmente no se usa
        List<Usuario> usuarios = dUsr.getAll();
        session.setAttribute("usuarios", usuarios);
        session.setAttribute("admin", "Administrador");
    }

}
