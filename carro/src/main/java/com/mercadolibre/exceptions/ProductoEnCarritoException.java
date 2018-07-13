/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mercadolibre.exceptions;

import com.mercadolibre.pojos.Carrito;
import com.mercadolibre.pojos.Producto;
import com.mercadolibre.util.HibernateUtil;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author hp desktop
 */
public class ProductoEnCarritoException {
    public void validarExistenciaProductoEnCarrito(int idcarrito, int idproducto)throws MercadoLibreException{
        Carrito carro = this.getSpecificCarrito(idcarrito);
        if(carro.getProductos().contains(new Producto(idproducto))){
            throw new MercadoLibreException("el producto ya esta en el carrito");
        }
    }
    public void validarInexistenciaProductoEnCarrito(int idcarrito, int idproducto)throws MercadoLibreException{
        Carrito carro = this.getSpecificCarrito(idcarrito);
        if(!carro.getProductos().contains(new Producto(idproducto))){
            throw new MercadoLibreException("el producto no esta en el carrito");
        }
    }
    
    private Carrito getSpecificCarrito(int idcarrito) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ses = sf.openSession();
        Carrito carrito = (Carrito) ses.get(Carrito.class, idcarrito);
        Hibernate.initialize(carrito.getUsuario());
        Hibernate.initialize(carrito.getProductos());
        ses.close();
        return carrito;
    } 
}
