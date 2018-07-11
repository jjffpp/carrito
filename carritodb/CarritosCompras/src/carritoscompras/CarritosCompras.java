/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carritoscompras;

import pojos.Carrito;

/**
 *
 * @author hp desktop
 */
public class CarritosCompras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CarritoH ch= new CarritoH();
        Carrito c = ch.eliminarProducto(1, 2);
        System.out.println(c.carritoAsString());
    }
    
}
