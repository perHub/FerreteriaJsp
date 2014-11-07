/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import org.hibernate.annotations.DiscriminatorOptions;

/**
 *
 * @author Peri
 */
@ManagedBean
@RequestScoped
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "tipousr",
        discriminatorType = DiscriminatorType.STRING
)

@DiscriminatorValue("cliente")
@DiscriminatorOptions(force = true)
@Table(name = "usuarios")
public class Cliente extends Usuario implements Serializable {

    private Integer dni;
    private Integer telefono;
    private String direccion;
    private Set<Compra> compras = new HashSet<>();

    public Cliente() {
    }

    public Cliente(String username, String nombre, String apellido, int dni) {
        super(username, nombre, apellido);
        this.dni = dni;
    }

    public Cliente(int id, String username, String nombre, String apellido, int dni) {
        this(username, nombre, apellido, dni);
        this.id = id;
    }

    public Cliente(String username, String password, String nombre, String apellido, int dni) {
        this(username, nombre, apellido, dni);
        this.password = password;
    }

    public Cliente(int id, String username, String password, String nombre, String apellido, int dni) {
        this(username, password, nombre, apellido, dni);
        this.id = id;

    }

    @Column(name = "dni")
    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Compra> getCompras() {
        return compras;
    }

    public void setCompras(Set<Compra> compras) {
        this.compras = compras;
    }

    @Column(name = "telefono")
    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
    
    @Column(name = "direccion")
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public void agregarCompra(Compra c) {
        compras.add(c);
    }

}
