/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Exceptions;

/**
 *
 * @author Peri
 */
public class AdminClienteException extends Exception { //Con esta excepción determino si se ha intentado acceder a un lugar diseñado para clientes o para admins.
    
    private boolean admin;
    
    public AdminClienteException(boolean admin){
       super("Imposible realizar esta solicitud para el usuario logueado.");
        this.admin = admin;
    }
    
    public AdminClienteException(){
       super("Imposible realizar esta solicitud para el usuario logueado.");
    }
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
