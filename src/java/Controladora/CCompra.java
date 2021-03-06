/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;

import DAO.DAOCompra;
import Modelo.Compra;
import Modelo.Detalle;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Peri
 */
public class CCompra {

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

}
