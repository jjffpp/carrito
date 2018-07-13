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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Controller {
    public int altaCarro (int dni, int promo){
        AltaCarrito ac = new AltaCarrito();  
        int id = ac.altaCarrito(dni, promo);
        return id;   
    } 

    public void eliminarCarrito(int idcarrito){
        EliminarCarrito ec = new EliminarCarrito();
        ec.borrarCarrito(idcarrito);
    }

    public ArrayList<Object> agregarProductoACarrito(int idcarrito, int idproducto){
        AgregarProducto ap = new AgregarProducto();
        Carrito carro = ap.agregarProducto(idcarrito, idproducto);
        float total = calcularTotal(carro.getProductos());
        MontoEstado me = new MontoEstado(carro, total);
        CarritoConMonto ccm = me.finalizacion();
        HashMap<String, String> mapa = me.productosJson();
        ArrayList<Object> resp = new ArrayList<>();
        resp.add(ccm);
        resp.add(mapa);
        return resp;
        
    }
  
    public ArrayList<Object> eliminarProductoACarrito(int idcarrito, int idproducto){
        EliminarProducto ep = new EliminarProducto();
        Carrito carro = ep.eliminarProducto(idcarrito, idproducto);
        float total = calcularTotal(carro.getProductos());
        MontoEstado me = new MontoEstado(carro, total);
        CarritoConMonto ccm = me.finalizacion();
        HashMap<String, String> mapa = me.productosJson();
        ArrayList<Object> resp = new ArrayList<>();
        resp.add(ccm);
        resp.add(mapa);
        return resp;
    }
    
    private float calcularTotal (Set<Producto> prod){
     float total = 0;
        for (Producto p : prod) {
            total += p.getValor();
        }   
        return total;
    }
}
