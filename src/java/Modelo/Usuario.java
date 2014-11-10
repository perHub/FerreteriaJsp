/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import org.hibernate.annotations.DiscriminatorOptions;

/**
 *
 * @author Peri
 */
@ManagedBean
@SessionScoped
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorOptions(force=true)
@DiscriminatorColumn(
        name = "tipousr",
        discriminatorType = DiscriminatorType.STRING
)

//@Table(name="usuarios")

public  class Usuario {

    protected int id;
    protected String username;
    protected String password;
    protected String nombre;
    protected String apellido;
    protected String email;
    protected Boolean activo;
    
    public Usuario(){
        
    }
    public Usuario(String username, String nombre, String apellido) {
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.activo = true;
    }
    public Usuario(int id, String username, String nombre, String apellido) {
        this(username, nombre, apellido);
        this.id = id;
    }
    
    public Usuario(String username, String password, String nombre, String apellido) {
        this(username, nombre, apellido);
        this.password = password;
    }
    
    public Usuario(int id, String username, String password, String nombre, String apellido) {
        this(username, password, nombre, apellido);
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

    @Column(name = "username", unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "apellido")
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name = "activo")
    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
    @Column(name = "email", unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  public Boolean esAdmin() throws Exception{
      if(this.getClass().equals(Administrador.class))
      {
          return true;
      }
      else if(this.getClass().equals(Cliente.class)){
          return false;
      }
      throw new Exception("Nada");
  }
}
