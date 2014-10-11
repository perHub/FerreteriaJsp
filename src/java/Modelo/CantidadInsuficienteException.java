/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author Peri
 */
public class CantidadInsuficienteException extends Exception{
    private Producto p;

    public Producto getP() {
        return p;
    }

    public void setP(Producto p) {
        this.p = p;
    }

    public CantidadInsuficienteException(Producto p) {
        super("No existe cantidad suficiente de ese producto.");
        this.p = p;
    }
    
    
    
}
