/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mercadolibre.model;

import com.mercadolibre.pojos.Carrito;
import com.mercadolibre.pojos.Producto;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.mercadolibre.util.HibernateUtil;

/**
 *
 * @author hp desktop
 */
public class EliminarProducto {
    //falta redefinir equals en Pojo.Producto
    public Carrito eliminarProducto(int idcarrito, int idproducto) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ses = sf.openSession();
        Carrito carrito = this.getSpecificCarrito(idcarrito);
        Producto specificProd = new Producto();
        for (Producto p : carrito.getProductos()) {
            if (p.getIdproducto() == idproducto) {
                specificProd = p;
            }
        }
        carrito.getProductos().remove(specificProd);
        Transaction tx = ses.beginTransaction();
        ses.saveOrUpdate(carrito);
        tx.commit();
        Carrito c = this.getSpecificCarrito(idcarrito);
        ses.close();
        return c;
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
