/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Peri
 */
@Entity
@Table(name = "detalles")
public class Detalle implements Serializable {

    private int id;
    private Producto producto;
    private int cantidad;
    private float precio = 0;
    private static int cont = 0;

    public Detalle() {
    }


    public Detalle(Producto producto, int cantidad) throws CantidadInsuficienteException {
        agregarProducto(producto, cantidad); // Tambien se encarga de setear el precio.
        this.cantidad = cantidad;
        this.id = cont++; //Necesito generar ids para identificar los objetos creados en memoria, pero estos ids son completamente independientes de la base de datos.
    }

    public Detalle(int id, Producto producto, int cantidad) throws CantidadInsuficienteException {
        this(producto, cantidad);
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

    @OneToOne
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Column(name = "cantidad")
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Column(name = "precio")
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void agregarProducto(Producto p, int cantidad) throws CantidadInsuficienteException {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad no puede ser cero ni negativa.");
        }
        if (cantidad > p.getStock()) {
            throw new CantidadInsuficienteException(p);
        }

        p.setStock(p.getStock() - cantidad);
        this.precio = precio + p.getPrecio() * cantidad;
        this.producto = p;
    }

    public void devolverProducto() {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad no puede ser cero ni negativa.");
        }
        producto.setStock(producto.getStock() + cantidad);
        this.precio = precio - producto.getPrecio() * cantidad;
    }
}
