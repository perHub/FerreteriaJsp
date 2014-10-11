/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.DiscriminatorOptions;

/**
 *
 * @author Peri
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "tipousr",
        discriminatorType = DiscriminatorType.STRING
)

@DiscriminatorValue("cliente")
@DiscriminatorOptions(force=true)
@Table(name = "usuarios")
public class Cliente extends Usuario implements Serializable{
    
    private Set<Compra> compras = new HashSet<>();

    private Integer dni;

    public Cliente(){}
    
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

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    public Set<Compra> getCompras() {
        return compras;
    }

    public void setCompras(Set<Compra> compras) {
        this.compras = compras;
    }
    
    public void agregarCompra(Compra c){
        compras.add(c);
    }
    
}
