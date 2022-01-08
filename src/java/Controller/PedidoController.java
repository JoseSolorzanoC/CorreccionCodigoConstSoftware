/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PedidoDao;
import Models.Pedido;

/**
 *
 * @author churri
 */
//Se corrijieron también iregularidades en los mensajes de retorno de 
//procesado.. a Procesando...
public class PedidoController {

    //Se renombró variable de DAO para mejor lectura y entendimiento
    //de la misma
    PedidoDao pedidoDao;
    String message;

    public PedidoController() {
        this.message = "";
    }

    public String insertarPedido(String estado, String id_usuario, String total) {
        pedidoDao = new PedidoDao();
        Pedido pedido = new Pedido();

        this.message = "Error en los parametros de entrada";

        pedido.setEstado(estado);
        pedido.setIdUsuario(id_usuario);
        pedido.setTotal(total);
        
        //validacion de los descuentos
        int descuento = Integer.parseInt(pedidoDao.cantidadVentasPorMes(id_usuario));
        pedido.setDescuento(descuento == 0 ? descuento : (descuento > 0 && descuento < 11) ? descuento : 10);

        if (pedidoDao.insertarPedido(pedido)) {
            this.message = "Procesando...";
        } else {
            this.message = "Error de base de datos";
        }
        return this.message;
    }

    public String listarPedidos(String id_usuario) {
        pedidoDao = new PedidoDao();
        return pedidoDao.listarPedidos(id_usuario);
    }
    
    public String listarPedidosCancelados(String id_usuario) {
        pedidoDao = new PedidoDao();
        return pedidoDao.listarPedidosCancelados(id_usuario);
    }
    
    public String listarPedidosTienda(String id_usuario){
        pedidoDao = new PedidoDao();
        Pedido pe = new Pedido();
        
        pe.setIdUsuario(id_usuario);
        return pedidoDao.listarPedidosTienda(pe);
    }
    
    public String cancelarPedido (String id_pedido){
        pedidoDao = new PedidoDao();
        if(pedidoDao.cancelarPedido(id_pedido)){
                this.message = "Pedido cancelado correctamente";
        }else{
            this.message = "Error";
        }
        return this.message;
    }
    
    public String despacharPedido (String id_pedido){
        pedidoDao = new PedidoDao();
        if(pedidoDao.despacharPedido(id_pedido)){
                this.message = "Pedido despachado correctamente";
        }else{
            this.message = "Error";
        }
        return this.message;
    }
    
    public String cantidadDescuento(String id_usuario){
        pedidoDao = new PedidoDao();
        int descuento = Integer.parseInt(pedidoDao.cantidadVentasPorMes(id_usuario));
        int descuento_response = (descuento == 0 ? descuento : (descuento > 0 && descuento < 11) ? descuento : 10);
        return  String.valueOf(descuento_response);
    }
    
}
