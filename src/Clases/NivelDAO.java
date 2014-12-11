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
public class NivelDAO {
    public static int Codigo(){
        int codigo = 0;
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select count(*) as total from niveles");
            ResultSet rs = consulta.executeQuery();
            if(rs.next()){
                codigo = rs.getInt("total") + 1;
            }
        } catch (SQLException ex) {
            
        }
        return codigo;
    }
    
    public static boolean Guardar(Nivel nuevo){
        try {
            String insert = "insert into niveles values(?,?)";
            PreparedStatement consulta = miConexion.prepareStatement(insert);
            consulta.setInt(1, nuevo.getCodigo());
            consulta.setString(2, nuevo.getNombre());
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static boolean ActualizarNivel(Nivel edit){
        try {
            String update = "update niveles set nombre=? where codigo=?";
            PreparedStatement consulta = miConexion.prepareStatement(update);
            consulta.setString(1, edit.getNombre());
            consulta.setInt(2, edit.getCodigo());
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static boolean EliminarNivel(int codigo){
        try {
            String delete = "delete from niveles where codigo=?";
            PreparedStatement consulta = miConexion.prepareStatement(delete);
            consulta.setInt(1, codigo);
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static void ObtenerNiveles(ArrayList<String> listado){
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select * from niveles order by codigo");
            ResultSet rs = consulta.executeQuery();
            while(rs.next()){
                listado.add(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
        }
    }
}
