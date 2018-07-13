/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mercadolibre.exceptions;

import com.mercadolibre.pojos.Carrito;
import com.mercadolibre.pojos.Usuario;
import com.mercadolibre.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author hp desktop
 */
public class IdCarritoEx {
    public void validarIdCarrito(int idcarrito)throws MercadoLibreException{
        List<Carrito> listaC = this.getAllCarrito();
        if(!listaC.contains(new Carrito(idcarrito))){
            throw new MercadoLibreException("el carrito no existe en la base de datos");
        }
    }
    
    private List<Carrito> getAllCarrito(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ses = sf.openSession();
        List<Carrito> lista = ses.createCriteria(Carrito.class).list();
        ses.close();
        return lista;
    }  
}
