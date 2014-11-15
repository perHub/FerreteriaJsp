/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;

import DAO.DAOPedido;
import Modelo.Pedido;
import java.util.List;
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
@ManagedBean(name = "cPedido")
@RequestScoped
public class CPedido {
    
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

    DAOPedido dP = new DAOPedido();

    public Pedido read(int id) {
        return dP.read(id);
    }

    public void agregar(Pedido p) {
        dP.create(p);
    }

    public void actualizar(Pedido p) {
        dP.update(p);
    }

    public List getAll() {
        return dP.getAll();
    }
//Los métodos de arriba deberán ser eliminados enventualmente.
//**********************************************************
//Los siguientes métodos ayudan a las relgas de navegación:
//**********************************************************
    public String ordenPiqueo(){
        
    int pedido = Integer.parseInt(request.getParameter("pedido"));
    
    Pedido p = dP.read(pedido);
    
    request.setAttribute("idPedido", pedido);
    request.setAttribute("prodsPick", p.obtenerProductos());
    
    return "piqueo";
}
//************************************************************************************
//Los siguientes métodos realizan tareas no relacionadas con las reglas de navegacion:
//************************************************************************************
    
}
