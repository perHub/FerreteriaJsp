/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Peri
 */
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    private int id;
    private String nombre;
    private float precio;
    private int stock;
    private boolean activo;

    public Producto() {
    }

    public Producto(String nombre, float precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto(int id, String nombre, float precio, int stock) {
        this(nombre, precio, stock);
        this.id = id;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "nombre", unique = true)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "precio")
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Column(name = "stock")
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Column(name = "activo")
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void reduceStock(int cantidad) throws CantidadInsuficienteException {
        if (cantidad > stock) {
            throw new CantidadInsuficienteException(this);
        }
        this.stock -= cantidad;
    }

}
