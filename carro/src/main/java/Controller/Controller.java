/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Set;
import model.AgregarProducto;
import model.AltaCarrito;
import pojos.CarritoConMonto;
import model.EliminarCarrito;
import model.EliminarProducto;
import model.MontoEstado;
import pojos.Carrito;
import pojos.Producto;


public class Controller {
    
    public int altaCarroConFechaPromocionable (int dni){
        AltaCarrito ac = new AltaCarrito();
        int idNuevoCarro = ac.altaCarrito(dni, 1);
        return idNuevoCarro;
    } 
    public int altaCarroSinPromocion (int dni){
        AltaCarrito ac = new AltaCarrito();
        int idNuevoCarro = ac.altaCarrito(dni, 0);
        return idNuevoCarro;
    }
    public void eliminarCarrito(int idcarrito){
        EliminarCarrito ec = new EliminarCarrito();
        try{
            ec.borrarCarrito(idcarrito);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }    
    }
    public CarritoConMonto agregarProductoACarrito(int idcarrito, int idproducto){
        AgregarProducto ap = new AgregarProducto();
        try{
            Carrito carro = ap.agregarProducto(idcarrito, idproducto);
            float total = calcularTotal(carro.getProductos());
            MontoEstado me = new MontoEstado(carro, total);
            CarritoConMonto ccm = me.finalizacion();
            return ccm;        
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public CarritoConMonto eliminarProductoACarrito(int idcarrito, int idproducto){
        EliminarProducto ep = new EliminarProducto();
        try{
            Carrito carro = ep.eliminarProducto(idcarrito, idproducto);
            float total = calcularTotal(carro.getProductos());
            MontoEstado me = new MontoEstado(carro, total);
            CarritoConMonto ccm = me.finalizacion();
            return ccm;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    private float calcularTotal (Set<Producto> prod){
     float total = 0;
        for (Producto p : prod) {
            total += p.getValor();
        }   
        return total;
    }
}
