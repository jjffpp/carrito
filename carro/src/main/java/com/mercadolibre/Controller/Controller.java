/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mercadolibre.Controller;

import com.google.gson.Gson;
import com.mercadolibre.model.AgregarProducto;
import com.mercadolibre.model.AltaCarrito;
import com.mercadolibre.model.EliminarCarrito;
import com.mercadolibre.model.EliminarProducto;
import com.mercadolibre.model.MontoEstado;
import com.mercadolibre.pojos.Carrito;
import com.mercadolibre.pojos.CarritoConMonto;
import com.mercadolibre.pojos.IdRespuesta;
import com.mercadolibre.pojos.Producto;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
    
    @RequestMapping("/welcome")
    public String welcome() {//Welcome page, non-rest
        String hola=  "Welcome to carrito de compras!";
        return hola;
    }
    @RequestMapping(value="/alta-Carrito", method=RequestMethod.GET)
    public ResponseEntity altaCarro (@RequestParam("dni") int dni, @RequestParam("promo") int promo){
        AltaCarrito ac = new AltaCarrito();
        IdRespuesta ir = new IdRespuesta();
        ir.setId(ac.altaCarrito(dni, promo));
	Gson gson = new Gson();
	final String representacionJSON = gson.toJson(ir);
        return new ResponseEntity(representacionJSON, HttpStatus.OK);
    } 
    @RequestMapping(value="/eliminar-Carrito", method=RequestMethod.GET)
    public ResponseEntity eliminarCarrito(@RequestParam("idcarrito")int idcarrito){
        EliminarCarrito ec = new EliminarCarrito();
        try{
            ec.borrarCarrito(idcarrito);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }    
    }
    @RequestMapping(value="/agregar-Producto", method=RequestMethod.GET)
    public ResponseEntity agregarProductoACarrito(@RequestParam("idcarrito")int idcarrito, @RequestParam("idproducto")int idproducto){
        AgregarProducto ap = new AgregarProducto();
        try{
            Carrito carro = ap.agregarProducto(idcarrito, idproducto);
            float total = calcularTotal(carro.getProductos());
            MontoEstado me = new MontoEstado(carro, total);
            CarritoConMonto ccm = me.finalizacion();
            Gson gson = new Gson();
            final String representacionJSON = gson.toJson(ccm);
            return new ResponseEntity(representacionJSON, HttpStatus.OK);        
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @RequestMapping(value="/eliminar-Producto", method=RequestMethod.GET)
    public ResponseEntity eliminarProductoACarrito(@RequestParam("idcarrito")int idcarrito, @RequestParam("idproducto")int idproducto){
        EliminarProducto ep = new EliminarProducto();
        try{
            Carrito carro = ep.eliminarProducto(idcarrito, idproducto);
            float total = calcularTotal(carro.getProductos());
            MontoEstado me = new MontoEstado(carro, total);
            CarritoConMonto ccm = me.finalizacion();
            Gson gson = new Gson();
            final String representacionJSON = gson.toJson(ccm);
            return new ResponseEntity(representacionJSON, HttpStatus.OK);    
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
      
    }
    private float calcularTotal (Set<Producto> prod){
     float total = 0;
        for (Producto p : prod) {
            total += p.getValor();
        }   
        return total;
    }
}
