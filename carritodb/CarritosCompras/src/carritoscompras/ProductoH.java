/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carritoscompras;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojos.Producto;
import util.HibernateUtil;

/**
 *
 * @author hp desktop
 */
public class ProductoH {
    public ProductoH(){}
     //devuelve un producto especifico a partir de un id producto
    public Producto getSpecificProducto (int id){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ses = sf.openSession();
        Producto prod = (Producto) ses.get(Producto.class, id);
        ses.close();
        return prod;
    } 
   //devuelve la lista de productos que hay en la base de datos
    public List<Producto> getAllProducto(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ses = sf.openSession();
        List<Producto> lista = ses.createCriteria(Producto.class).list();
        ses.close();
        return lista;
    }//da de alta un producto en la base de datos
    public void guardarProducto(Producto p){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ses = sf.openSession();
        Transaction tx  = ses.beginTransaction();
        ses.saveOrUpdate(p);
        tx.commit();
        ses.close();
    }
}
