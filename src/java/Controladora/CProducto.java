/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;

import DAO.DAOProducto;
import Modelo.Producto;
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
@ManagedBean(name = "CProducto")
@RequestScoped
public class CProducto {

    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

    DAOProducto dProd = new DAOProducto();

    public void create(Producto p) {
        dProd.create(p);
    }

    public void update(Producto p) {
        dProd.update(p);
    }

    public List<Producto> getAll() {
        return dProd.getAll();
    }

    public List<Producto> getAllActivated() {
        return dProd.getAllActivated();
    }

    public Map<Integer, Producto> getAllMap() {
        return dProd.getAllMap();
    }

//Los métodos de arriba deberán ser eliminados enventualmente.
//**********************************************************
//Los siguientes métodos ayudan a las relgas de navegación:
//**********************************************************
    public String prepararProducto() {

        String rtr = "error";

        if (!requestParams.isEmpty()) {
            int id = Integer.parseInt(requestParams.get("id"));
            Producto p = dProd.read(id);
            request.setAttribute("producto", p);

            rtr = "EditProd";
        }

        return rtr;

    }

    public String saveProd() {
        Producto p = (Producto) request.getAttribute("producto");

        dProd.update(p);

        return "productos";
    }

//************************************************************************************
//Los siguientes métodos realizan tareas no relacionadas con las reglas de navegacion:
//************************************************************************************
    public void clearProd() {

        if (requestParams.isEmpty()) {
            Producto p = new Producto();
            p.setActivo(true);
            request.setAttribute("producto", p);
        }
    }
}
