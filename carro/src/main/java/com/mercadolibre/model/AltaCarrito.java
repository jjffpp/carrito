/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mercadolibre.model;

import com.mercadolibre.pojos.Carrito;
import com.mercadolibre.pojos.Usuario;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.mercadolibre.util.HibernateUtil;

/**
 *
 * @author hp desktop
 */
public class AltaCarrito {
    public AltaCarrito(){}
    //(promo=1 -> fecha promocionable; promo=0 -> sin fecha promocionable)
    //da de alta un carrito 
    //en la base de datos y devuelve el id del carrito creado
    public int altaCarrito(int dni, int promo){
        Usuario user = this.getSpecificUsuario(dni);
        Carrito c = new Carrito(user, promo, null);
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ses = sf.openSession();
        Transaction tx  = ses.beginTransaction();
        ses.saveOrUpdate(c);
        tx.commit();
        List<Carrito> lista = this.getAllCarrito();
        ses.close();
        return lista.get(lista.size()-1).getIdcarrito();
    }
    private Usuario getSpecificUsuario (int dni){
      SessionFactory sf = HibernateUtil.getSessionFactory();
      Session ses = sf.openSession();
      Usuario user = (Usuario) ses.get(Usuario.class, dni);
      ses.close();
      return user;
    } 
    private List<Carrito> getAllCarrito(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ses = sf.openSession();
        List<Carrito> lista = ses.createCriteria(Carrito.class).list();
        ses.close();
        return lista;
    }
}