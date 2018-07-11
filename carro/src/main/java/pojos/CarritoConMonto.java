/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;


/**
 *
 * @author hp desktop
 */
public class CarritoConMonto {
    private String estado;
    private Carrito carrito;
    private float monto;

    public CarritoConMonto(Carrito carrito, float monto, String estado) {
        this.carrito = carrito;
        this.monto = monto;
        this.estado = estado;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
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
