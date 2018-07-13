/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mercadolibre.exceptions;

import com.mercadolibre.pojos.Producto;
import com.mercadolibre.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author hp desktop
 */
public class IdProductoEx {
    public void validarIdProducto(int idproducto)throws MercadoLibreException{
        List<Producto> listaP = this.getAllProducto();
        if(!listaP.contains(new Producto(idproducto))){
            throw new MercadoLibreException("el producto no existe en la base de datos");
        }
    }
    
    private List<Producto> getAllProducto(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ses = sf.openSession();
        List<Producto> lista = ses.createCriteria(Producto.class).list();
        ses.close();
        return lista;
    }
}
