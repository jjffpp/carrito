/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mercadolibre.view;

import com.google.gson.Gson;
import com.mercadolibre.Controller.Controller;
import com.mercadolibre.exceptions.DniUsuarioEx;
import com.mercadolibre.exceptions.FechaPromocionableEx;
import com.mercadolibre.exceptions.IdCarritoEx;
import com.mercadolibre.exceptions.IdProductoEx;
import com.mercadolibre.exceptions.MercadoLibreException;
import com.mercadolibre.model.ProductoMasCaros;
//import com.mercadolibre.exceptions.ProductoEnCarritoEx;
import com.mercadolibre.pojos.CarritoConMonto;
import com.mercadolibre.pojos.IdRespuesta;
import com.mercadolibre.pojos.Producto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewRestController {

    @RequestMapping(value = "/4productos-mas-caros", method = RequestMethod.GET)
    public ResponseEntity productosMasCarosV(@RequestParam("dni") int dni) {
        ProductoMasCaros pmc = new ProductoMasCaros();
        HashMap<String, String> prods = pmc.getProductosMasCaros(dni);
        Gson gson = new Gson();
        String representacionJSON = gson.toJson(prods);
        return new ResponseEntity(representacionJSON, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/alta-Carrito", method = RequestMethod.GET)
    public ResponseEntity altaCarroV(@RequestParam("dni") int dni, @RequestParam("promo") int promo) {
        DniUsuarioEx due = new DniUsuarioEx();
        FechaPromocionableEx fpe = new FechaPromocionableEx();
        IdRespuesta ir = new IdRespuesta();
        Controller controller = new Controller();
        try {
            due.validarDni(dni);
            fpe.validarFechaPromocionable(promo);
            ir.setId(controller.altaCarro(dni, promo));
            Gson gson = new Gson();
            String representacionJSON = gson.toJson(ir);
            return new ResponseEntity(representacionJSON, HttpStatus.OK);
        } catch (MercadoLibreException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/eliminar-Carrito", method = RequestMethod.GET)
    public ResponseEntity eliminarCarritoV(@RequestParam("idcarrito") int idcarrito) {
        IdCarritoEx ice = new IdCarritoEx();
        Controller controller = new Controller();
        try {
            ice.validarIdCarrito(idcarrito);
            controller.eliminarCarrito(idcarrito);
            return new ResponseEntity(HttpStatus.OK);
        } catch (MercadoLibreException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/agregar-Producto", method = RequestMethod.GET)
    public ResponseEntity agregarProductoACarritoV(@RequestParam("idcarrito") int idcarrito, @RequestParam("idproducto") int idproducto) {
        IdCarritoEx ice = new IdCarritoEx();
        IdProductoEx ipe = new IdProductoEx();
        //ProductoEnCarritoEx pece = new ProductoEnCarritoEx();
        Controller controller = new Controller();
        try {
            ice.validarIdCarrito(idcarrito);
            ipe.validarIdProducto(idproducto);
           // pece.validarInexistenciaProductoEnCarrito(idcarrito, idproducto);
            ArrayList<Object> respuesta = controller.agregarProductoACarrito(idcarrito, idproducto);
            String representacionJSON = this.representacionJSONProductosCarritos((CarritoConMonto)respuesta.get(0),(HashMap<String, String>)respuesta.get(1));
            return new ResponseEntity(representacionJSON, HttpStatus.OK);
        } catch (MercadoLibreException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/eliminar-Producto", method = RequestMethod.GET)
    public ResponseEntity eliminarProductoACarritoV(@RequestParam("idcarrito") int idcarrito, @RequestParam("idproducto") int idproducto) {
        IdCarritoEx ice = new IdCarritoEx();
        IdProductoEx ipe = new IdProductoEx();
        //ProductoEnCarritoEx pece = new ProductoEnCarritoEx();
        Controller controller = new Controller();
        try {
            ice.validarIdCarrito(idcarrito);
            ipe.validarIdProducto(idproducto);
            //pece.validarExistenciaProductoEnCarrito(idcarrito, idproducto);
            ArrayList<Object> respuesta = controller.eliminarProductoACarrito(idcarrito, idproducto);
            String representacionJSON = this.representacionJSONProductosCarritos((CarritoConMonto)respuesta.get(0),(HashMap<String, String>)respuesta.get(1));
            return new ResponseEntity(representacionJSON, HttpStatus.OK);
        } catch (MercadoLibreException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    private String representacionJSONProductosCarritos(CarritoConMonto ccm, HashMap<String, String> mapa) {
        Gson gson = new Gson();
        String formaJson1 = gson.toJson(ccm);
        String formaJson2 = gson.toJson(mapa);
        return formaJson1 + formaJson2;
    }

}
