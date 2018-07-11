/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojos.Carrito;
import pojos.Usuario;
import util.HibernateUtil;

/**
 *
 * @author hp desktop
 */
@Path("/altaCarrito")
public class AltaCarrito {
    public AltaCarrito(){}
    //(promo=1 -> fecha promocionable; promo=0 -> sin fecha promocionable)
    //da de alta un carrito 
    //en la base de datos y devuelve el id del carrito creado
    @POST
    @Path("/insert")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
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