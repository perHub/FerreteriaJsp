/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;

import DAO.DAOCompra;
import DAO.DAOPedido;
import Exceptions.NoException;
import Modelo.CantidadInsuficienteException;
import Modelo.Compra;
import Modelo.Detalle;
import Modelo.Pedido;
import Modelo.Producto;
import com.sun.faces.context.RequestParameterMap;
import java.util.Date;
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
@ManagedBean(name = "cCompra")
@RequestScoped
public class CCompra {

    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

    DAOCompra dC = new DAOCompra();

    public List<Compra> getAll() {
        return dC.getAll();
    }

    public Map getAllMap() {
        return dC.getAllMap();
    }

    public List<Compra> getPendientes() {
        return dC.getPendientes();
    }

    public List<Compra> getProcesados() {
        return dC.getProcesados();
    }

    public List<Compra> getAllByUserId(int id) {
        return dC.getAllbyUsrId(id);
    }

    public List<Detalle> getDetalles(int id) {
        return dC.getDetalles(id);
    }

    //Los métodos de arriba deberán ser eliminados enventualmente.
//**********************************************************
//Los siguientes métodos ayudan a las relgas de navegación:
//**********************************************************
    public String prepararDetalle() {

        String rta = "error";

        if (!requestParams.isEmpty()) {
            Compra c = (Compra) request.getAttribute("c");
            List detalles = dC.getDetalles(c.getId());
            session.setAttribute("detalles", detalles);

            rta = "detalles";
        }
        return rta;
    }

    public String procesarPedidos() throws CantidadInsuficienteException, NoException {
        if (!requestParams.containsValue("on")) {
            throw new NoException("No.");
        }

        Pedido p = new Pedido(new Date());

        for (Map.Entry<String, String> mapStr : requestParams.entrySet()) { //Proceso los pedidos
            if (mapStr.getValue().equals("on")) {
                Compra c = dC.read(Integer.parseInt(mapStr.getKey()));
                c.setProcesado(Boolean.TRUE);
                c.setPedido(p);
                p.getCompras().put(c.getId(), c);
            }
        }

        Map<Producto, Integer> prodCant = p.obtenerProductos();

        for (Map.Entry<Producto, Integer> prod : prodCant.entrySet()) {
            prod.getKey().reduceStock(prod.getValue());
        }

        DAOPedido dP = new DAOPedido();
        dP.create(p); //Persisto

        return "procesados";
    }
}
