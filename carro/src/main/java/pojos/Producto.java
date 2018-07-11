package pojos;
// Generated 10/07/2018 15:18:06 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Producto generated by hbm2java
 */
public class Producto  implements java.io.Serializable {


     private Integer idproducto;
     private String nombre;
     private int valor;
     private Set<Carrito> carritos = new HashSet<Carrito>(0);

    public Producto() {
    }

	
    public Producto(int valor) {
        this.valor = valor;
    }
    public Producto(String nombre, int valor, Set<Carrito> carritos) {
       this.nombre = nombre;
       this.valor = valor;
       this.carritos = carritos;
    }
   
    public Integer getIdproducto() {
        return this.idproducto;
    }
    
    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getValor() {
        return this.valor;
    }
    
    public void setValor(int valor) {
        this.valor = valor;
    }
    public Set<Carrito> getCarritos() {
        return this.carritos;
    }
    
    public void setCarritos(Set<Carrito> carritos) {
        this.carritos = carritos;
    }




}


