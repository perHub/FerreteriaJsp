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
public class loginException extends Exception {
    public loginException(){
        super("No se encuentra logueado.");
    }
}
