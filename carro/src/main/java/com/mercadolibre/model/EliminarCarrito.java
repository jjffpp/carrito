/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mercadolibre.model;

import com.mercadolibre.pojos.Carrito;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mercadolibre.util.HibernateUtil;
import java.util.List;

/**
 *
 * @author hp desktop
 */
public class EliminarCarrito {

    public void borrarCarrito(int idcarrito) {
        Carrito carrito = this.getSpecificCarrito(idcarrito);
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ses = sf.openSession();
        ses.delete(carrito);
        ses.beginTransaction().commit();
        ses.close();
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
