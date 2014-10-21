/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;

/**
 *
 * @author Peri
 */
@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

    private int id;
    private Date fecha;
    private Map<Integer, Compra> compras = new HashMap<>();

    public Pedido() {
    }
    public Pedido(Date fecha) {
        this.fecha = fecha;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "fecha")
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pedido")
    @MapKey(name = "id")
    public Map<Integer, Compra> getCompras() {
        return compras;
    }

    public void setCompras(Map compras) {
        this.compras = compras;
    }

    public void agregarCompra(Compra c) {
        if (c != null && !c.getProcesado()) {
            compras.put(c.getId(), c);
        }
    }

    public Map<String, Integer> obtenerProductos() {

        Map<String, Integer> mapProd = new HashMap<>(); //Key: Nombre, value: cantidad (del detalle)

        for (Map.Entry<Integer, Compra> c : compras.entrySet()) {
            for (Detalle d : c.getValue().getDetalles()) {

                Integer cantAnterior;
                int cantDetalle = d.getCantidad();
                String nombreProd = d.getProducto().getNombre();

                if ((cantAnterior = mapProd.get(nombreProd)) != null) { // Uso esta comprobación porque es mas flexible que el método ContainsKey en este caso.
                    mapProd.put(nombreProd, cantAnterior + cantDetalle);
                } else {
                    mapProd.put(nombreProd, cantDetalle);
                }
            }
        }
        return mapProd;
    }

}
