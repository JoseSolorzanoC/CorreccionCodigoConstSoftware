/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controller.UsuarioController;
import Models.UserSession;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author churri
 */
//Se modificaron nombres de variables para que cumplan con cammelCase
//Y se re-estructuro código para que lleve un correcto orden y separación
@WebServlet(name = "SessionServlet", urlPatterns = {"/SessionServlet"})
public class SessionServlet extends HttpServlet {

    private UserSession userSession = null;
    private String opcion = "-1";
    private String responseServer = "{}";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
        switch (opcion) {
            case "log":
                UsuarioController usuarioController = new UsuarioController();
                String usuario = request.getParameter("user");
                String clave = request.getParameter("pass");
                
                userSession = usuarioController.login(usuario, clave);
                
                if (userSession != null) {
                    HttpSession session = request.getSession();
                    
                    session.setAttribute("usuario", userSession);
                    
                    if (userSession.getEstado().equals("a")) {
                        if (userSession.getCargo().equals("tienda")) {
                            responseServer = "{\"redirect\":\"tienda.html\"}";
                        } else if (userSession.getCargo().equals("cliente")) {
                            responseServer = "{\"redirect\":\"cliente.html\"}";
                        } else {
                            responseServer = "{\"redirect\":\"admin.html\"}";
                        }
                    } else {
                        responseServer = "{\"redirect\":\"index.html\"}";
                    }
                } else {
                    responseServer = "{\"redirect\":\"index.html\"}";
                }
                break;
            case "ses":
                userSession = (UserSession) request.getSession().getAttribute("usuario");
                
                if (userSession != null) {
                    String nombre = String.format("%s %s", userSession.getNombre().trim(), userSession.getApellido().trim());
                    String infoUser = String.format("{\"getNombres\":\"%s\",\"getNombreUsuario\":\"%s\",\"getCargo\":\"%s\"}", nombre, userSession.getNombreUsuario(), userSession.getCargo());
                    if (userSession.getCargo().equals("tienda")) {
                        responseServer = "{\"redirect\":\"tienda.html\",\"userObject\":" + infoUser + "}";
                    } else if (userSession.getCargo().equals("cliente")) {
                        responseServer = "{\"redirect\":\"cliente.html\",\"userObject\":" + infoUser + "}";
                    } else {
                        responseServer = "{\"redirect\":\"admin.html\",\"userObject\":" + infoUser + "}";
                    }
                } else {
                    responseServer = "{\"redirect\":\"index.html\"}";
                }
                break;
            default: //cerrar sesion
                request.getSession().setAttribute("usuario", null);
                
                responseServer = "{\"redirect\":\"index.html\",\"permiso\":\"nothing\"}";
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
