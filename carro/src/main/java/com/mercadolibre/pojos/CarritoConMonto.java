/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mercadolibre.pojos;



/**
 *
 * @author hp desktop
 */
public class CarritoConMonto {
    private String estado;
    private Integer idcarrito;
    private float monto;

    public CarritoConMonto(Carrito carrito, float monto, String estado) {
        this.idcarrito = carrito.getIdcarrito();
        this.monto = monto;
        this.estado = estado;
        
    }

    public Integer getIdcarrito() {
        return idcarrito;
    }

    public void setIdcarrito(Integer idcarrito) {
        this.idcarrito = idcarrito;
    }
    
    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
