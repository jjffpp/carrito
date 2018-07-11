package com.mercadolibre.pojos;
// Generated 10/07/2018 15:18:06 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Carrito generated by hbm2java
 */
public class Carrito  implements java.io.Serializable {


     private Integer idcarrito;
     private Usuario usuario;
     private int promo;
     private Set<Producto> productos = new HashSet<Producto>(0);

    public Carrito() {
    }

	
    public Carrito(Usuario usuario, int promo) {
        this.usuario = usuario;
        this.promo = promo;
    }
    public Carrito(Usuario usuario, int promo, Set<Producto> productos) {
       this.usuario = usuario;
       this.promo = promo;
       this.productos = productos;
    }
   
    public Integer getIdcarrito() {
        return this.idcarrito;
    }
    
    public void setIdcarrito(Integer idcarrito) {
        this.idcarrito = idcarrito;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public int getPromo() {
        return this.promo;
    }
    
    public void setPromo(int promo) {
        this.promo = promo;
    }
    public Set<Producto> getProductos() {
        return this.productos;
    }
    
    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }




}


