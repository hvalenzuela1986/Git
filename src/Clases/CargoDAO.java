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
public class CargoDAO {
    public static int Codigo(){
        int codigo = 0;
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select count(*) as total from cargos");
            ResultSet rs = consulta.executeQuery();
            if(rs.next()){
                codigo = rs.getInt("total") + 1;
            }
        } catch (SQLException ex) {
            
        }
        return codigo;
    }
    
    public static boolean Guardar(Cargo nuevo){
        try {
            String insert = "insert into cargos values(?,?)";
            PreparedStatement consulta = miConexion.prepareStatement(insert);
            consulta.setInt(1, nuevo.getCodigo());
            consulta.setString(2, nuevo.getNombre());
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static boolean ActualizarCargo(Cargo edit){
        try {
            String update = "update cargos set nombre=? where codigo=?";
            PreparedStatement consulta = miConexion.prepareStatement(update);
            consulta.setString(1, edit.getNombre());
            consulta.setInt(2, edit.getCodigo());
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static boolean EliminarCargo(int codigo){
        try {
            String delete = "delete from cargos where codigo=?";
            PreparedStatement consulta = miConexion.prepareStatement(delete);
            consulta.setInt(1, codigo);
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static void ObtenerCargos(ArrayList<String> listado){
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select * from cargos order by codigo");
            ResultSet rs = consulta.executeQuery();
            while(rs.next()){
                listado.add(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
        }
    }
    
    public static String ObtenerNombre(int codigo){
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select nombre from cargos where codigo = ?");
            consulta.setInt(1, codigo);
            ResultSet rs = consulta.executeQuery();
            if(rs.next()){
                return rs.getString("nombre");
            }
        } catch (SQLException ex) {
        }
        return null;
    }
    
    public static int ObtenerCodigo(String nombre){
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select codigo from cargos where nombre = ?");
            consulta.setString(1, nombre);
            ResultSet rs = consulta.executeQuery();
            if(rs.next()){
                return Integer.parseInt(rs.getString("codigo"));
            }
        } catch (SQLException ex) {
        }
        return -1;
    }
}
