/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mercadolibre.model;

import com.mercadolibre.pojos.Carrito;
import com.mercadolibre.pojos.CarritoConMonto;
import com.mercadolibre.pojos.Producto;
import java.util.Set;


public class MontoEstado {

    private Carrito carro;
    private float total;

    public MontoEstado(Carrito carro, float total) {
        this.carro = carro;
        this.total = total;
    }

    private String estadoCarro() {
        String estado = "";
        if (carro.getUsuario().getVip() == 1) {
            estado = "promocionable por usuario vip";
        } else {
            if (carro.getPromo() == 1) {
                estado = "promocionable por fecha especial";
            } else {
                estado = "comun";
            }
        }
        return estado;
    }

    private float productoMenorValor(Set<Producto> setProd) {
        Producto prodMenor = new Producto(9999);
        for (Producto p : setProd) {
            if (p.getValor() < prodMenor.getValor()) {
                prodMenor = p;
            }
        }
        return prodMenor.getValor();
    }
    private float montoMas10productos (String estado){
        float montoFinal = 0;
        if (estado.equals("comun")) {
                montoFinal = total - 200;
            }
            if (estado.equals("promocionable por fecha especial")) {
                montoFinal = total - 500;
            }
            if (estado.equals("promicionable por usuario vip")) {
                float prodMenor = this.productoMenorValor(carro.getProductos());
                montoFinal = total - prodMenor - 700;
            }
        return montoFinal;    
    }
    public CarritoConMonto finalizacion() {
        float montoFinal=0;
        String estado = this.estadoCarro();
        if (carro.getProductos().size() >= 10) {
            montoFinal = this.montoMas10productos(estado);
        }
        if (carro.getProductos().size() == 5){
            montoFinal = total - (float)(total*0.2);
        }
        CarritoConMonto ccm = new CarritoConMonto(carro, montoFinal, estado);
        return ccm;
    }

}
