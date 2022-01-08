/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DetallePedidoDao;
import Models.DetallePedido;

/**
 *
 * @author churri
 */
//Se corrijieron también iregularidades en los mensajes de retorno de 
//procesado.. a Procesando...
public class DetallePedidoController {

    //Se renombró variable de DAO para mejor lectura y entendimiento
    //de la misma
    DetallePedidoDao detPedDao;
    String message;

    public DetallePedidoController() {
        this.message = "";
    }

    public String insertarDetallePedido(String id_producto, String cantidad, String precio) {
        detPedDao = new DetallePedidoDao();
        DetallePedido detm = new DetallePedido();
        this.message = "Error en los parametros ingresados";

        detm.setCantidad(cantidad);
        detm.setPrecio(precio);
        detm.setIdProducto(id_producto);

        if (detPedDao.insertarDetallePedido(detm)) {
            this.message = "Pedido realizado con exito";
        } else {
            this.message = "Error de base de datos";
        }
        return this.message;
    }

    public String disminuirStock(String id_producto, String id_cantidad) {
        detPedDao = new DetallePedidoDao();
        DetallePedido detPed = new DetallePedido();
        this.message = "Error en los parametros ingresados";

        detPed.setIdProducto(id_producto);
        detPed.setCantidad(id_cantidad);

        if (detPedDao.disminuirStock(detPed)) {
            this.message = "Stock actualizado correctamente";
        } else {
            this.message = "Error de base de datos";
        }
        return this.message;
    }
    
    public String aumentarStock(String id_producto, String id_cantidad) {
        detPedDao = new DetallePedidoDao();
        DetallePedido detm = new DetallePedido();
        this.message = "Error en los parametros ingresados";

        detm.setIdProducto(id_producto);
        detm.setCantidad(id_cantidad);

        if (detPedDao.aumentarStock(detm)) {
            this.message = "Stock actualizado correctamente";
        } else {
            this.message = "Error de base de datos";
        }
        return this.message;
    }

}
