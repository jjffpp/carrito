/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mercadolibre.exceptions;

import com.mercadolibre.pojos.Usuario;
import com.mercadolibre.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author hp desktop
 */
public class DniUsuarioEx {
    public void validarDni(int dni)throws MercadoLibreException{
        List<Usuario> listaU = this.getAllUsuario();
        if(!listaU.contains(new Usuario(dni))){
            throw new MercadoLibreException("el usuario no existe en la base de datos");
        }
    }
    
    private List<Usuario> getAllUsuario(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session ses = sf.openSession();
        List<Usuario> lista = ses.createCriteria(Usuario.class).list();
        ses.close();
        return lista;
    }
}
