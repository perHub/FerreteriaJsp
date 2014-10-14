/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladora;

import DAO.DAOProducto;
import Modelo.Producto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Peri
 */
public class CProducto {
    
    DAOProducto dProd = new DAOProducto();
    
    public List<Producto> getAll(){
        return dProd.getAll();
    }
    public List<Producto> getAllActivated(){
        return dProd.getAllActivated();
    }
    public Map<Integer, Producto> getAllMap(){
        return dProd.getAllMap();
    }
}
