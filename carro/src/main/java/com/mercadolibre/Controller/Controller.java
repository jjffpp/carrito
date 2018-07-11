/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mercadolibre.Controller;

import com.mercadolibre.model.AgregarProducto;
import com.mercadolibre.model.AltaCarrito;
import com.mercadolibre.model.EliminarCarrito;
import com.mercadolibre.model.EliminarProducto;
import com.mercadolibre.model.MontoEstado;
import com.mercadolibre.pojos.Carrito;
import com.mercadolibre.pojos.CarritoConMonto;
import com.mercadolibre.pojos.Producto;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
    
    @RequestMapping("/welcome")
    public String welcome() {//Welcome page, non-rest
        String hola=  "Welcome to carrito de compras!";
        return hola;
    }
    @RequestMapping(value="/altaCarrito/{dni}", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity altaCarroConFechaPromocionable (@PathVariable("dni") int dni){
        AltaCarrito ac = new AltaCarrito();
        int idNuevoCarro = ac.altaCarrito(dni, 1);
        return new ResponseEntity(idNuevoCarro, HttpStatus.OK);
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