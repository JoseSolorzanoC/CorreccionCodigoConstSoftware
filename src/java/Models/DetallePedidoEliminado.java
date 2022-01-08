/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

/**
 * 
 * @author churri
 */
//Se modificaron propiedades de la clase y getters y setter para que usen 
//cammelCase
public class DetallePedidoEliminado {
    private String idDetallePedidoEliminado = "";
    private String idPedidoEliminado = "";
    private String idProducto = "";
    private String cantidad = "";
    private String precio = "";

    public DetallePedidoEliminado() {
    }

    public String getIdDetallePedidoEliminado() {
        return idDetallePedidoEliminado;
    }

    public void setIdDetallePedidoEliminado(String idDetallePedidoEliminado) {
        this.idDetallePedidoEliminado = idDetallePedidoEliminado;
    }

    public String getIdPedidoEliminado() {
        return idPedidoEliminado;
    }

    public void setIdPedidoEliminado(String idPedidoEliminado) {
        this.idPedidoEliminado = idPedidoEliminado;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    
}
