/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DataStatic.Conection;
import Models.DetallePedido;

/**
 *
 * @author churri
 */
public class DetallePedidoDao {

    //Se renombró variable de conexión para mejor lectura y entendimiento
    //de la misma
    Conection dbConn;
    String sql = "";

    public DetallePedidoDao() {
        dbConn = new Conection();
    }

    public boolean insertarDetallePedido(DetallePedido detp) {
        sql = String.format("insert into detalle_pedido (producto_id_producto, cantidad, precio_unit, encabezado_pedido_id_encapedido) values (%s, %s, %s, (select id_encapedido from encabezado_pedido order by id_encapedido desc limit 1))", detp.getIdProducto(), detp.getCantidad(), detp.getPrecio());
        return dbConn.modifyBD(sql);
    }

    public boolean cancelarPedido(DetallePedido detp) {
        sql = String.format("delete from detalle_pedido where encabezado_pedido_id_encapedido = %s", detp.getIdPedido());
        return dbConn.modifyBD(sql);
    }

    public boolean disminuirStock(DetallePedido detp) {
        sql = String.format("update producto set stock = stock - %s where id_producto = %s", detp.getCantidad(), detp.getIdProducto());
        return dbConn.modifyBD(sql);
    }
    
    public boolean aumentarStock(DetallePedido detp) {
        sql = String.format("update producto set stock = stock + %s where id_producto = %s", detp.getCantidad(), detp.getIdProducto());
        return dbConn.modifyBD(sql);
    }

}
