/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mercadolibre.model;

import com.mercadolibre.pojos.Carrito;
import com.mercadolibre.pojos.Producto;
import com.mercadolibre.pojos.Usuario;
import com.mercadolibre.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ProductoMasCaros {
    private Usuario getSpecificUsuario (int dni){
      SessionFactory sf = HibernateUtil.getSessionFactory();
      Session ses = sf.openSession();
      Usuario user = (Usuario) ses.get(Usuario.class, dni);
      Hibernate.initialize(user.getCarritos());
      ses.close();
      return user;
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
    private Carrito getInitProductos (Carrito c){
      SessionFactory sf = HibernateUtil.getSessionFactory();
      Session ses = sf.openSession();
      Hibernate.initialize(c.getProductos());
      ses.close();
      return c;
    }
    private Set<Producto> getProductosOrdenadosPorValor(int dni){
        Set<Producto> productos = new HashSet<Producto>(0);
        Usuario user = this.getSpecificUsuario(dni);
        for (Carrito c : user.getCarritos()) {
           productos.addAll(c.getProductos());
        }
        Collections.sort((List<Producto>) productos);
        return productos;
    }
    public HashMap<String, String> getProductosMasCaros(int dni){
        List<Producto> p = (List<Producto>)this.getProductosOrdenadosPorValor(dni);
        HashMap<String, String> productos = new  HashMap<>();
        productos.put(p.get(1).getIdproducto().toString(),p.get(1).getNombre());
        productos.put(p.get(2).getIdproducto().toString(),p.get(2).getNombre());
        productos.put(p.get(3).getIdproducto().toString(),p.get(3).getNombre());
        productos.put(p.get(4).getIdproducto().toString(),p.get(4).getNombre());
        return productos;
    }
 
}
