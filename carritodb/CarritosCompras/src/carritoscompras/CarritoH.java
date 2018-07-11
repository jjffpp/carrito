/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carritoscompras;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojos.Carrito;
import pojos.Producto;
import pojos.Usuario;
import util.HibernateUtil;

/**
 *
 * @author hp desktop
 */
public class CarritoH {
    //devuelve todos los carritos de la base de datos
    public List<Carrito> getAllCarrito(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ses = sf.openSession();
        List<Carrito> lista = ses.createCriteria(Carrito.class).list();
        ses.close();
        return lista;
    }
    //(promo=1 -> fecha promocionable; promo=0 -> sin fecha promocionable)
    //da de alta un carrito 
    //en la base de datos y devuelve el id del carrito creado
    public int altaCarrito(int dni, int promo){
        UsuarioH ub = new UsuarioH();
        Usuario user = ub.getSpecificUsuario(dni);
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
    
    //devuelve un carrito especifico
    public Carrito getSpecificCarrito (int dni){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ses = sf.openSession();
        Carrito carrito = (Carrito) ses.get(Carrito.class, dni);
        Hibernate.initialize(carrito.getUsuario());
        Hibernate.initialize(carrito.getProductos());
        ses.close();
        return carrito;
    }
    //borra un carrito especifico
      //borra integridad referencial de la tabla carritoproducto
    public void borrarCarrito (int idcarrito){
        Carrito carrito = this.getSpecificCarrito(idcarrito);
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ses = sf.openSession();
        ses.delete(carrito);
        ses.beginTransaction().commit();
        ses.close();
    }
    //agrega un producto a un carrito especifico
    //agrega el producto a la tabla carritoproducto
    public Carrito agregarProducto (int idcarrito, int idproducto){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ses = sf.openSession();
        Carrito carrito = this.getSpecificCarrito(idcarrito);
        ProductoH pb = new ProductoH();
        Producto producto = pb.getSpecificProducto(idproducto);
        carrito.getProductos().add(producto);
        Transaction tx  = ses.beginTransaction();
        ses.saveOrUpdate(carrito);
        tx.commit();
        Carrito c = this.getSpecificCarrito(idcarrito);
        ses.close();
        return c;
    }
    //borra un producto especifico de un carrito especifico
    //borra de la tabla carritoproducto
    public Carrito eliminarProducto(int idcarrito, int idproducto){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ses = sf.openSession();
        Carrito carrito = this.getSpecificCarrito(idcarrito);
        Producto specificProd = new Producto();
        for (Producto p : carrito.getProductos()){
            if (p.getIdproducto() == idproducto ){
                specificProd = p;
            }
        }
        carrito.getProductos().remove(specificProd);       
        Transaction tx  = ses.beginTransaction();
        ses.saveOrUpdate(carrito);
        tx.commit();
        Carrito c = this.getSpecificCarrito(idcarrito);
        ses.close();
        return c;
    }
}
