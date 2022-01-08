/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.PedidoEliminadoDao;
import Models.PedidoEliminado;

/**
 * 
 * @author churri
 */
//Se corrijieron también iregularidades en los mensajes de retorno de 
//procesado.. a Procesando...
public class PedidoEliminadoController {
    
    //Se renombró variable de DAO para mejor lectura y entendimiento
    //de la misma
    PedidoEliminadoDao pedElimDao;
    String message;
    
    public PedidoEliminadoController (){
        this.message = "";
    }
    
    public String insertarPedidoEliminado(String fecha_pedido, String id_usuario, String descuento, String total){
        pedElimDao = new PedidoEliminadoDao();
        PedidoEliminado pedElim = new PedidoEliminado();
        
        this.message = "Error en los parametros ingresados";
        pedElim.setFechaPedido(fecha_pedido);
        pedElim.setIdUsuario(id_usuario);
        pedElim.setDescuento(descuento);
        pedElim.setTotal(total);
        
        if(pedElimDao.insertarPedidoEliminado(pedElim)){
            this.message = "Corecto";
        }else{
            this.message = "Error de base dedatos";
        }
        
        return this.message;
    }
    
}
