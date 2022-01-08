/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controller.DetallePedidoController;
import Controller.DetallePedidoEliminadoController;
import Controller.PedidoController;
import Controller.PedidoEliminadoController;
import Models.UserSession;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dúval Carvajal S.
 */

//Se modificaron nombres de variables para que cumplan con cammelCase
//Y se re-estructuro código para que lleve un correcto orden y separación
@WebServlet(name = "PedidoServlet", urlPatterns = {"/PedidoServlet"})
public class PedidoServlet extends HttpServlet {

    private UserSession userSession = null;
    private String responseServer = "{}";
    private String opcion = "-1";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(responseServer);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        opcion = request.getParameter("opcion");
        PedidoController pedcon = new PedidoController();
        userSession = (UserSession) request.getSession().getAttribute("usuario");
        switch (opcion) {
            case "listarpedidos":
                responseServer = pedcon.listarPedidos(userSession.getIdUser());
                break;
            case "listarpedidoscancelados":
                responseServer = pedcon.listarPedidosCancelados(userSession.getIdUser());
                break;
            case "listarpedidosTienda":
                responseServer = pedcon.listarPedidosTienda(userSession.getIdUser());
                break;
            case "insertarPedido":
                String estado = "p";
                String detalle = request.getParameter("detalle");
                String total = request.getParameter("total");
                
                System.out.println(userSession.getIdUser());
                String status = pedcon.insertarPedido(estado, userSession.getIdUser(), total);
                
                if (status.equals("Procesando...")) {
                    String[] detalle_0 = detalle.split("/");
                    for (int i = 0; i < detalle_0.length; i++) {
                        String[] detalle_1 = detalle_0[i].split(";");
                        DetallePedidoController detpecon = new DetallePedidoController();
                        //detpecon.disminuirStock(detalle_1[0], detalle_1[1]);
                        responseServer = detpecon.insertarDetallePedido(detalle_1[0], detalle_1[1], detalle_1[2]);
                    }
                }
                break;
            case "cancelarPedido":
                //Declaración de variables
                PedidoController pedController = new PedidoController();
                DetallePedidoEliminadoController depel = new DetallePedidoEliminadoController();
                DetallePedidoController detpeconxx = new DetallePedidoController();
                PedidoEliminadoController pedelx = new PedidoEliminadoController();
                
                String idPedido = request.getParameter("id_pedido");
                String fechaPedido = request.getParameter("fecha_pedido");
                String descuento = request.getParameter("descuento");
                String totalx = request.getParameter("total");
                
                pedelx.insertarPedidoEliminado(fechaPedido, userSession.getIdUser(), descuento, totalx);
                
                System.out.println(depel.getProductos(idPedido));
                String[] detalle0 = depel.getProductos(idPedido).split("/");
                
                for (int i = 0; i < detalle0.length; i++) {
                    String[] detalle1 = detalle0[i].split(";");
                    System.out.println(detalle1[0] + " - " + detalle1[1]);
                    detpeconxx.aumentarStock(detalle1[0], detalle1[1]);
                    depel.insertarDetallePedidoEliminado(detalle1[1], detalle1[2], detalle1[0]);
                }

                responseServer = pedController.cancelarPedido(idPedido);
                break;
            case "cancelarPedidoTienda":
                //Declaración de variables
                DetallePedidoEliminadoController detPedElim = new DetallePedidoEliminadoController();
                DetallePedidoController detPedController = new DetallePedidoController();
                PedidoEliminadoController pedElimController = new PedidoEliminadoController();
                PedidoController pedidoController = new PedidoController();

                String idPedidoT = request.getParameter("id_pedido");
                String fechaPedidoT = request.getParameter("fecha_pedido");
                String descuentoT = request.getParameter("descuento");
                String totalxT = request.getParameter("total");
                String idUsuario = request.getParameter("id_usuario");

                pedElimController.insertarPedidoEliminado(fechaPedidoT, idUsuario, descuentoT, totalxT);

                System.out.println(detPedElim.getProductos(idPedidoT));
                String[] detalle_0t = detPedElim.getProductos(idPedidoT).split("/");

                for (int i = 0; i < detalle_0t.length; i++) {
                    String[] detalle_1 = detalle_0t[i].split(";");
                    System.out.println(detalle_1[0] + " - " + detalle_1[1]);
                    detPedController.aumentarStock(detalle_1[0], detalle_1[1]);
                    detPedElim.insertarDetallePedidoEliminado(detalle_1[1], detalle_1[2], detalle_1[0]);
                }

                responseServer = pedidoController.cancelarPedido(idPedidoT);
                break;
            case "despacharPedido":
                String id_pedidos = request.getParameter("id_pedido");
                
                responseServer = pedcon.despacharPedido(id_pedidos);
                break;

            case "disminuiStock":
                DetallePedidoController detPedidoController = new DetallePedidoController();
                String id_producto = request.getParameter("id_producto");
                String cantidad = request.getParameter("cantidad");
                
                responseServer = detPedidoController.disminuirStock(id_producto, cantidad);
                break;

            case "aumentarStock":
                DetallePedidoController detPedidoController_ = new DetallePedidoController();
                String id_productox = request.getParameter("id_producto");
                String cantidadx = request.getParameter("cantidad");
                
                responseServer = detPedidoController_.aumentarStock(id_productox, cantidadx);
                break;
            case "cantidadDescuento":
                responseServer = pedcon.cantidadDescuento(userSession.getIdUser());
                break;
            default:
                break;
        }
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
