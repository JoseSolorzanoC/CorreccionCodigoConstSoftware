/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import DataStatic.Conection;
import Models.DetallePedidoEliminado;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author churri
 */
public class DetallePedidoEliminadoDao {
    
    //Se renombró variable de conexión para mejor lectura y entendimiento
    //de la misma
    Conection dbConn;
    String sql = "";
    
    public DetallePedidoEliminadoDao(){
        dbConn = new Conection();
    }
    
    public boolean insertarDetallePedidoEliminado(DetallePedidoEliminado detpe){
        sql = String.format("insert into detalle_pedido_eliminado (cantidad, precio_unit, encabezado_pedido_eliminado_id_pedeliminado, producto_id_producto)"
                + "values(%s, %s, (select id_pedeliminado from encabezado_pedido_eliminado order by id_pedeliminado desc limit 1), %s)",detpe.getCantidad(), detpe.getPrecio(), 
                detpe.getIdProducto());
        return dbConn.modifyBD(sql);
    }
    
    public DefaultTableModel getProductos (String id_pedido){
        sql = "select * from detalle_pedido where encabezado_pedido_id_encapedido = "+id_pedido+"";
        System.out.println(sql);
        return dbConn.returnRecord(sql);
    }
    
}
