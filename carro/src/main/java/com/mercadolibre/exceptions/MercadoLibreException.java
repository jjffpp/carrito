/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mercadolibre.exceptions;

/**
 *
 * @author hp desktop
 */
public class MercadoLibreException extends Exception{
        public static final long serialVersionUID = 700L;
    
    public MercadoLibreException(String mensaje){
        super(mensaje);
    }
}
