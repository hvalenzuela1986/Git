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
public class PerfilDAO {
    public int Codigo(){
        int codigo = 0;
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select count(*) as total from perfiles");
            ResultSet rs = consulta.executeQuery();
            if(rs.next()){
                codigo = rs.getInt("total") + 1;
            }
        } catch (SQLException ex) {
            
        }
        return codigo;
    }
    
    public boolean Guardar(Perfil nuevo){
        try {
            String insert = "insert into perfiles values(?,?)";
            PreparedStatement consulta = miConexion.prepareStatement(insert);
            consulta.setInt(1, nuevo.getCodigo());
            consulta.setString(2, nuevo.getNombre());
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public boolean ActualizarPerfil(Perfil edit){
        try {
            String update = "update perfiles set nombre=? where codigo=?";
            PreparedStatement consulta = miConexion.prepareStatement(update);
            consulta.setString(1, edit.getNombre());
            consulta.setInt(2, edit.getCodigo());
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public boolean EliminarPerfil(int codigo){
        try {
            String delete = "delete from perfiles where codigo=?";
            PreparedStatement consulta = miConexion.prepareStatement(delete);
            consulta.setInt(1, codigo);
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public void ObtenerPerfiles(ArrayList<String> listado){
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select * from perfiles order by codigo");
            ResultSet rs = consulta.executeQuery();
            while(rs.next()){
                listado.add(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
        }
    }
    
}
