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
public class FechaPromocionableEx {
    public void validarFechaPromocionable(int promo)throws MercadoLibreException{
        if(!((promo==0)||(promo==1))){
            throw new MercadoLibreException("no es un valor esperado para la fecha promocionable");
        }
    }
}
