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
import pojos.Carrito;
import pojos.Producto;
import pojos.Usuario;
import util.HibernateUtil;

/**
 *
 * @author hp desktop
 */
public class UsuarioH {
    public UsuarioH(){}
    //devuelve un usario especifico
    public Usuario getSpecificUsuario (int id){
      SessionFactory sf = HibernateUtil.getSessionFactory();
      Session ses = sf.openSession();
      Usuario user = (Usuario) ses.get(Usuario.class, id);
      ses.close();
      return user;
    } 

}
