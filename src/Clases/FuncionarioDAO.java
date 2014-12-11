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
import javax.swing.JOptionPane;

/**
 *
 * @author Optimus
 */
public class FuncionarioDAO {
    public static boolean Guardar(Funcionario nuevo){
        try {
            String insert = "insert into funcionarios values(?,?,?,?,?,?,?)";
            PreparedStatement consulta = miConexion.prepareStatement(insert);
            consulta.setString(1, nuevo.getRut());
            consulta.setString(2, nuevo.getNombre());
            consulta.setString(3, nuevo.getFechaNac());
            consulta.setInt(4, nuevo.getCargo());
            consulta.setString(5, nuevo.getFechaIngreso());
            consulta.setInt(6, nuevo.getNivel());
            consulta.setInt(7, nuevo.getEstado());
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static boolean Editar(Funcionario edit){
        try {
            String update= "update funcionarios set nombre = ?, fecha_nac =?, cargo=?, fecha_ingreso=?, nivel=?, estado=? where rut=?";
            PreparedStatement consulta = miConexion.prepareStatement(update);
            consulta.setString(1, edit.getNombre());
            consulta.setString(2, edit.getFechaNac());
            consulta.setInt(3, edit.getCargo());
            consulta.setString(4, edit.getFechaIngreso());
            consulta.setInt(5, edit.getNivel());
            consulta.setInt(6, edit.getEstado());
            consulta.setString(7, edit.getRut());
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static void ObtenerFuncionarios(ArrayList<Funcionario> listado){
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select * from funcionarios where estado = 1 order by fecha_ingreso desc");
            ResultSet rs = consulta.executeQuery();
            while(rs.next()){
                String rut = rs.getString("rut");
                String nombre = rs.getString("nombre");
                String fechaNac = rs.getString("fecha_nac");
                int cargo = rs.getInt("cargo");
                String fechaIngreso = rs.getString("fecha_ingreso");
                int nivel = rs.getInt("nivel");
                int estado = rs.getInt("estado");
                Funcionario func = new Funcionario(rut, nombre, fechaNac, cargo, fechaIngreso, nivel, estado);
                listado.add(func);
            }
        } catch (SQLException ex) {
        }
    }
    
    public static Funcionario BuscarFuncionario(String rut){
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select * from funcionarios where rut = ?");
            consulta.setString(1, rut);
            ResultSet rs = consulta.executeQuery();
            if(rs.next()){
                String nombre = rs.getString("nombre");
                String fechaNac = rs.getString("fecha_nac");
                int cargo = rs.getInt("cargo");
                String fechaIngreso = rs.getString("fecha_ingreso");
                int nivel = rs.getInt("nivel");
                int estado = rs.getInt("estado");
                Funcionario func = new Funcionario(rut, nombre, fechaNac, cargo, fechaIngreso, nivel, estado);
                return func;
            }
        } catch (SQLException ex) {
        }
        return null;
    }
    
    public static boolean EliminarFuncionario(String rut){
        try {
            String insert = "update funcionarios set estado = 0 where rut = ?";
            PreparedStatement consulta = miConexion.prepareStatement(insert);
            consulta.setString(1, rut);
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
}
