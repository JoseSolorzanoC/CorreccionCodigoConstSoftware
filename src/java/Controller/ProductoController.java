/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.ProductoDao;
import Models.Producto;

/**
 * 
 * @author churri
 */
//Se corrijieron también iregularidades en los mensajes de retorno de 
//procesado.. a Procesando...
public class ProductoController {
    
    //Se renombró variable de DAO para mejor lectura y entendimiento
    //de la misma
    ProductoDao productoDao;
    String message;
    
    public ProductoController (){
        this.message = "";
    }
    
    public String insertarProducto(String nombre, String stock, String precio_unit, String id_usuario){
        productoDao = new ProductoDao();
        Producto producto = new Producto();
        this.message = "Error en los parametros ingresados";
        producto.setNombre(nombre);
        producto.setCantidad(stock);
        producto.setPrecio(precio_unit);
        producto.setIdUsuario(id_usuario);
        if(productoDao.insertarProducto(producto)){
            this.message = "Producto agregado correctamente";
        }else{
            this.message = "Error al agregar producto.";
        }
        return this.message;
    }
    
    public String listarProductos(String id_usuario){
        productoDao = new ProductoDao();
        Producto producto = new Producto();
        producto.setIdUsuario(id_usuario);
        return productoDao.listarProductos(producto);
    }
    
    public String listarProductosTienda(String id_usuario){
        productoDao = new ProductoDao();
        Producto producto = new Producto();
        producto.setIdUsuario(id_usuario);
        return productoDao.listarProductosTienda(producto);
    }
    
}
