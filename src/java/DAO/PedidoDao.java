/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DataStatic.Conection;
import Models.Pedido;

/**
 *
 * @author churri
 */
public class PedidoDao {

    //Se renombró variable de conexión para mejor lectura y entendimiento
    //de la misma
    Conection dbConn;
    String sql = "";

    public PedidoDao() {
        dbConn = new Conection();
    }

    public boolean insertarPedido(Pedido pd) {
        sql = String.format("insert into encabezado_pedido (fecha_venta, estado, usuario_id_usuario, total, descuento) values (now(),'%s',%s, %s, %s)", pd.getEstado(), pd.getIdUsuario(), pd.getTotal(), pd.getDescuento());
        return dbConn.modifyBD(sql);
    }

    public String cantidadVentasPorMes(String id_usuario) {
        sql = String.format("select count(id_encapedido) \n"
                + "from encabezado_pedido where DATE_PART('day', now() - fecha_venta) <= 30 and usuario_id_usuario = %s", id_usuario);
        return dbConn.fillString(sql);
    }

    public boolean cancelarPedido(Pedido pd) {
        sql = String.format("delte from encabezado_pedido where id_encapedido = %s", pd.getIdPedido());
        return dbConn.modifyBD(sql);
    }

    public String listarPedidos(String id_usuario) {
        sql = "select distinct ep.id_encapedido,ep.fecha_venta, us.nombre_tienda, ep.total, ep.descuento, ep.estado\n"
                + "from encabezado_pedido as ep inner join detalle_pedido as dp on ep.id_encapedido = dp.encabezado_pedido_id_encapedido\n"
                + "inner join producto as pr on dp.producto_id_producto = pr.id_producto \n"
                + "inner join usuario as us on pr.usuario_id_usuario = us.id_usuario\n"
                + "where ep.usuario_id_usuario = " + id_usuario + "\n"
                + "group by ep.total, ep.id_encapedido, dp.id_detpedido, pr.id_producto, us.id_usuario "
                + "order by ep.id_encapedido asc";
        return dbConn.getRecordsInJson(sql);
    }

    public String listarPedidosCancelados(String id_usuario) {
        sql = "select distinct ep.id_pedeliminado, ep.fecha_pedido, ep.fecha_pedido, us.nombre_tienda, ep.total, ep.descuento, ep.descuento\n"
                + "from encabezado_pedido_eliminado as ep inner join detalle_pedido_eliminado as dp on ep.id_pedeliminado = dp.encabezado_pedido_eliminado_id_pedeliminado\n"
                + "inner join producto as pr on dp.producto_id_producto = pr.id_producto \n"
                + "inner join usuario as us on pr.usuario_id_usuario = us.id_usuario\n"
                + "where ep.usuario_id_usuario = " + id_usuario + "\n"
                + "group by ep.total, ep.id_pedeliminado, dp.id_detalle_pedelimnado, pr.id_producto, us.id_usuario \n"
                + "order by ep.id_pedeliminado asc";
        return dbConn.getRecordsInJson(sql);
    }

    public String listarPedidosTienda(Pedido pd) {
        sql = "select distinct ep.id_encapedido,ep.fecha_venta, ep.total, ep.descuento, ep.estado, ep.usuario_id_usuario\n"
                + "from encabezado_pedido as ep inner join detalle_pedido as dp on ep.id_encapedido = dp.encabezado_pedido_id_encapedido\n"
                + "inner join producto as pr on dp.producto_id_producto = pr.id_producto \n"
                + "inner join usuario as us on pr.usuario_id_usuario = us.id_usuario\n"
                + "where us.id_usuario = " + pd.getIdUsuario() + "\n"
                + "group by ep.total, ep.id_encapedido, dp.id_detpedido, pr.id_producto, us.id_usuario \n"
                + "order by ep.id_encapedido asc";
        System.out.println(sql);
        return dbConn.getRecordsInJson(sql);
    }

    public boolean cancelarPedido(String id_pedido) {
        sql = "delete from detalle_pedido where encabezado_pedido_id_encapedido = " + id_pedido + "";
        System.out.println(sql);
        if (dbConn.modifyBD(sql)) {
            sql = "delete from encabezado_pedido where id_encapedido = " + id_pedido + "";
        }
        return dbConn.modifyBD(sql);
    }

    public boolean despacharPedido(String id_pedido) {
        sql = "update encabezado_pedido set estado = 'd' where id_encapedido = " + id_pedido + "";
        System.out.println(sql);
        return dbConn.modifyBD(sql);
    }

}
