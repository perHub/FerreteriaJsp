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

/**
 *
 * @author Peri
 */
public class CCompra {
    DAOCompra dC = new DAOCompra();
    
    public List<Compra> getAllByUserId(int id){
        return dC.getAllbyUsrId(id);
    }
    
    public List<Detalle> getDetalles(int id){
        return dC.getDetalles(id);
    }
    
}
