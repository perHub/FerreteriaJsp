/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;

import DAO.DAOPedido;
import Modelo.Pedido;
import java.util.List;

/**
 *
 * @author Dev
 */
public class CPedido {
    DAOPedido dP = new DAOPedido();
    
    public Pedido read(int id){
       return dP.read(id);
    }
    public void agregar(Pedido p) {
        dP.create(p);
    }
    
    public void actualizar(Pedido p){
        dP.update(p);
    }
    public List getAll() {
        return dP.getAll();
    }
}
