package com.mercadolibre.pojos;
// Generated 11/07/2018 20:00:28 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private int dni;
     private String nombre;
     private int vip;
     private Set<Carrito> carritos = new HashSet<Carrito>(0);

    public Usuario() {
    }
    public Usuario(int dni){
        this.dni = dni;
    }
	
    public Usuario(int dni, int vip) {
        this.dni = dni;
        this.vip = vip;
    }
    public Usuario(int dni, String nombre, int vip, Set<Carrito> carritos) {
       this.dni = dni;
       this.nombre = nombre;
       this.vip = vip;
       this.carritos = carritos;
    }
   
    public int getDni() {
        return this.dni;
    }
    
    public void setDni(int dni) {
        this.dni = dni;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getVip() {
        return this.vip;
    }
    
    public void setVip(int vip) {
        this.vip = vip;
    }
    public Set<Carrito> getCarritos() {
        return this.carritos;
    }
    
    public void setCarritos(Set<Carrito> carritos) {
        this.carritos = carritos;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Usuario) {
            Usuario tmpUsuario = (Usuario) obj;
            if (this.dni == tmpUsuario.dni) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        } 
    }




}


