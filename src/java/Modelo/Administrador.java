/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
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
@DiscriminatorOptions(force=true)
@DiscriminatorValue("admin")
@Table(name = "usuarios")

public class Administrador extends Usuario implements Serializable {
    
    //el dni del admin no puede ser null
    public Administrador() {
    }
    
    public Administrador(String username, String password, String nombre, String apellido) {
        super(username, password, nombre, apellido);
    }

    public Administrador(int id, String username, String password, String nombre, String apellido) {
        super(id, username, password, nombre, apellido);
    }

}
