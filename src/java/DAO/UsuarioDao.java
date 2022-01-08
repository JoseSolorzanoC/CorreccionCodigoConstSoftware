/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import DataStatic.Conection;
import Models.Usuario;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author churri
 */
public class UsuarioDao {
    
    //Se renombró variable de conexión para mejor lectura y entendimiento
    //de la misma
    Conection dbConn;
    String sql = "";
    
    public UsuarioDao() {
        dbConn = new Conection();
    }
    
    public boolean insertarUsuario(Usuario us){
        sql = String.format("insert into usuario (nombres, apellidos, nombre_tienda, estado, tipo_usuario,"
                + "usuario, contrasenia) values('%s','%s','%s','%s','%s','%s','%s')",us.getNombres(),
                us.getApellidos(), us.getNombreTienda(), us.getEstado(), us.getTipoUsuario(), us.getUsuario(), us.getContrasenia());
        return dbConn.modifyBD(sql);
    }
    
    public boolean habilitar(Usuario us){
        sql = "update usuario set estado = 'a' where id_usuario = "+us.getIdUsuario()+"";
        return dbConn.modifyBD(sql);
    }
    
    public DefaultTableModel login(String nombre_user, String contrasenia){
        sql = "select * from usuario where usuario = '"+nombre_user+"' and contrasenia = '"+contrasenia+"'";
        System.out.println(sql);
        return dbConn.returnRecord(sql);
    }
    
    public String listarUsuarios(){
        sql = "select * from usuario order by id_usuario";
        return dbConn.getRecordsInJson(sql);
    }
   
    
}
