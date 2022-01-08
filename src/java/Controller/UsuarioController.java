/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsuarioDao;
import Models.UserSession;
import Models.Usuario;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author churri
 */
//Se corrijieron también iregularidades en los mensajes de retorno de 
//procesado.. a Procesando...
public class UsuarioController {

    //Se renombró variable de DAO para mejor lectura y entendimiento
    //de la misma
    UsuarioDao usuarioDao;
    String message;

    public UsuarioController() {
        message = "";
    }

    public String insertarUsuario(String nombres, String apellidos, String nombre_tienda, String estado, String tipo_usuario, String usuario, String contrasenia) {
        usuarioDao = new UsuarioDao();
        Usuario usuarioO = new Usuario();
        this.message = "Error en los parametros de entrada.";
        usuarioO.setNombres(nombres);
        usuarioO.setApellidos(apellidos);
        usuarioO.setNombreTienda(nombre_tienda);
        usuarioO.setEstado(estado);
        usuarioO.setTipoUsuario(tipo_usuario);
        usuarioO.setUsuario(usuario);
        usuarioO.setContrasenia(contrasenia);
        if(usuarioDao.insertarUsuario(usuarioO)){
            this.message = "Usuario registrado correctamente.";
        }else{
            this.message = "Error al agregar un nuevo usuario.";
        }
        return this.message;
    }
    
    public String habilitarUsuario (String id_usuario){
        usuarioDao = new UsuarioDao();
        Usuario usuario = new Usuario();
        this.message = "Error en los parametros de entrada.";
        usuario.setIdUsuario(id_usuario);
        if(usuarioDao.habilitar(usuario)){
            this.message = "Usuario habilitado correctamente";
        }else{
            this.message = "Error al habilitar el usuario";
        }
        return this.message;
    }
    
    public UserSession login(String usuario, String contrasenia){
        UserSession usr = null;
        usuarioDao = new UsuarioDao();
        DefaultTableModel employeeResponse = usuarioDao.login(usuario, contrasenia);
        if (employeeResponse.getRowCount() > 0) {
            usr = new UserSession();
            usr.setIdUser(employeeResponse.getValueAt(0, 0).toString());
            usr.setNombre(employeeResponse.getValueAt(0, 1).toString());
            usr.setApellido(employeeResponse.getValueAt(0, 2).toString());
            usr.setNombreUsuario(employeeResponse.getValueAt(0, 6).toString());
            usr.setCargo(employeeResponse.getValueAt(0, 5).toString());
            usr.setEstado(employeeResponse.getValueAt(0, 4).toString());
        } else {
            usr = null;
        }
        return usr;
    }
    
    public String listarUsuarios(){
        usuarioDao = new UsuarioDao();
        return usuarioDao.listarUsuarios();
    }

}
