/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import DataStatic.Conection;
import Models.Producto;

/**
 * 
 * @author churri
 */
public class ProductoDao {
    
    //Se renombró variable de conexión para mejor lectura y entendimiento
    //de la misma
    Conection dbConn;
    String sql = "";
    
    public ProductoDao(){
        dbConn = new Conection();
    }
    
    public boolean insertarProducto(Producto pc){
        sql = String.format("insert into producto(nombre, stock, precio_unit, usuario_id_usuario) values('%s', %s, %s, %s)",pc.getNombre(), pc.getCantidad(), pc.getPrecio(), pc.getIdUsuario());
        return dbConn.modifyBD(sql);
    }
    
    public String listarProductos(Producto pc){
        sql = "select * from producto where usuario_id_usuario = "+pc.getIdUsuario()+" and stock > 0 order by id_producto asc";
        return dbConn.getRecordsInJson(sql);
    }
    
    public String listarProductosTienda(Producto pc){
        sql = "select * from producto where usuario_id_usuario = "+pc.getIdUsuario()+" and stock > 0 order by id_producto asc";
        return dbConn.getRecordsInJson(sql);
    }

}
