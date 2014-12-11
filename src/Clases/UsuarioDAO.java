/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import static Sistema.Login.miConexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Optimus
 */
public class UsuarioDAO {
    public void ObtenerUsuarios(ArrayList<Usuario> listado){
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select * from usuarios where estado = 1 order by fecha_creacion desc");
            ResultSet rs = consulta.executeQuery();
            while(rs.next()){
                String rut = rs.getString("rut");
                String nombre = rs.getString("nombre");
                String clave = rs.getString("clave");
                int perfil = rs.getInt("perfil");
                int estado = rs.getInt("estado");
                String fecha = rs.getString("fecha_creacion");
                Usuario user = new Usuario(rut, nombre, clave, perfil, estado, fecha);
                listado.add(user);
            }
        } catch (SQLException ex) {
        }
    }
    
    public boolean EliminarUsuario(String rut){
        try {
            String insert = "update usuarios set estado = 0 where rut = ?";
            PreparedStatement consulta = miConexion.prepareStatement(insert);
            consulta.setString(1, rut);
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
}
