/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.DetallePedidoEliminadoDao;
import Models.DetallePedidoEliminado;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author churri
 */
//Se corrijieron también iregularidades en los mensajes de retorno de 
//procesado.. a Procesando...
public class DetallePedidoEliminadoController {
    
    //Se renombró variable de DAO para mejor lectura y entendimiento
    //de la misma
    DetallePedidoEliminadoDao detPedElimDao;
    String message;
    
    public DetallePedidoEliminadoController(){
        this.message = "";
    }
    
    public String insertarDetallePedidoEliminado(String cantidad, String precio, String id_producto){
        detPedElimDao = new DetallePedidoEliminadoDao();
        DetallePedidoEliminado detPedElim = new DetallePedidoEliminado();
        this.message = "Error en los parametros ingresados";
        
        detPedElim.setCantidad(cantidad);
        detPedElim.setPrecio(precio);
        detPedElim.setIdProducto(id_producto);
        
        if(detPedElimDao.insertarDetallePedidoEliminado(detPedElim)){
            this.message = "Correcto";
        }else{
            this.message = "Error en la base de datos";
        }
        
        return this.message;
    }
    
        public String getProductos(String id_pedido) {
        detPedElimDao = new DetallePedidoEliminadoDao();
        DefaultTableModel table = detPedElimDao.getProductos(id_pedido);
        String detalle = "";
        for (int i = 0; i < table.getRowCount(); i++) {
            detalle += table.getValueAt(i, 4).toString() + ";" + table.getValueAt(i, 1).toString() +";"+table.getValueAt(i, 3).toString();
            if (i < table.getRowCount() - 1) {
                detalle += "/";
            }
        }
        System.out.println(detalle);
        return detalle;
    }

}
