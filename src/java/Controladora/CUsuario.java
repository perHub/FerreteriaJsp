/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladora;

import DAO.*;
import Modelo.*;
import java.util.List;

/**
 *
 * @author Peri
 */
public class CUsuario {

    DAOUsuario dUsr = new DAOUsuario();
//   DAOAdministrador dAdm = new DAOAdministrador();
//   DAOCliente dCl = new DAOCliente();
    
    public Usuario read(int id) {
        return dUsr.read(id);
    }

    public void agregar(Usuario u) {
        dUsr.create(u);
    }
    
    public void actualizar(Usuario u){
        dUsr.update(u);
    }
    public List getAll() {
        return dUsr.getAll();
    }
    public Usuario login (String username, String password){
        return dUsr.login(username, password);
    }
    public Usuario getById(int id){
        return dUsr.read(id);
    }
            
}
