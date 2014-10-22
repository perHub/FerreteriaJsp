/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javassist.NotFoundException;
import javax.persistence.*;

/**
 *
 * @author Peri
 */
@Entity
@Table(name = "compras")

public class Compra implements Serializable {

    private int id;
    private Set<Detalle> detalles = new HashSet<>();
    private float total = 0;
    private Date fecha;
    private Boolean procesado;
    private Cliente cliente;
    private Pedido pedido;

    public Compra() {
    }

    public Compra(Date fecha, Detalle detalle, Cliente cl) {
        agregarDetalle(detalle);
        this.fecha = fecha;
        this.cliente = cl;
        this.procesado = false;
    }

    public Compra(int id, Date fecha, Detalle detalle, Cliente cl) {
        this(fecha, detalle, cl);
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

    @Column(name = "total")
    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "fecha")
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    //Preguntar
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(Set<Detalle> detalles) {
        this.detalles = detalles;
    }

    public Boolean agregarDetalle(Detalle d) {     //Los métodos agregar y eliminar detalle sólo deben ser utilizados para objetos creados directamente en memoria y no para aquellos creados por hibernate.
        this.total = total + d.getPrecio();
        Detalle detActual;
        
        if ((detActual = findByNombre(d.getProducto().getNombre())) != null) { //Sumo los precios y cantidades, no agrego el objeto.
            detActual.setPrecio(detActual.getPrecio() + d.getPrecio());
            detActual.setCantidad(detActual.getCantidad() + d.getCantidad());
            return false;
        }
        else{
            detalles.add(d);
            return true;    //Retorna verdadero si agrega un nuevo objeto
        }
    }

    @SuppressWarnings("null")
    public void eliminarDetalle(int id) throws NotFoundException {
        try {
            Detalle d = null;

            for (Detalle det : detalles) {
                if (det.getId() == id) {
                    d = det;
                }
            }

            if ((this.total = total - d.getPrecio()) <= 0) {
                this.total = 0;
            }

            d.devolverProducto();
            detalles.remove(d);
        } catch (NullPointerException ex) {
            throw new NotFoundException("No se ha encontrado el detalle de compra.");
        }
    }

    public Detalle findByNombre(String nombre) {
        
        for (Detalle d : detalles) {
            if (d.getProducto().getNombre().equals(nombre)) {
                return d;
            }
        }

        return null;
    }

    @Column(name = "procesado")
    public Boolean getProcesado() {
        return procesado;
    }

    public void setProcesado(Boolean procesado) {
        this.procesado = procesado;
    }

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
