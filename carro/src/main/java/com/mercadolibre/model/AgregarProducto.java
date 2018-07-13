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
public class AgregarProducto {

    public Carrito agregarProducto(int idcarrito, int idproducto) {
        Carrito carrito = this.getSpecificCarrito(idcarrito);
        Producto producto = this.getSpecificProducto(idproducto);
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ses = sf.openSession();
        carrito.getProductos().add(producto);
        Transaction tx = ses.beginTransaction();
        ses.saveOrUpdate(carrito);
        tx.commit();
        ses.close();
        Carrito c = this.getSpecificCarrito(idcarrito);
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

    private Producto getSpecificProducto(int id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ses = sf.openSession();
        Producto prod = (Producto) ses.get(Producto.class, id);
        ses.close();
        return prod;
    }

}

