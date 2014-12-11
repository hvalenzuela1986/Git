/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Optimus
 */
public class ConexionBD {
    public static Connection GetConnection(){
        Connection conexion = null;
        String mensaje = "Existe un problema con la conexión a la base de datos. La aplicación se cerrará.";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost/sistema_java";
            String usuarioDB = "root";
            String passwordDB = "";
            conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);
        }
        catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, mensaje, "Error en la Conexión con la BD", JOptionPane.ERROR_MESSAGE);
            conexion = null;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, mensaje, "Error en la Conexión con la BD", JOptionPane.ERROR_MESSAGE);
            conexion = null;
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, mensaje, "Error en la Conexión con la BD", JOptionPane.ERROR_MESSAGE);
            conexion = null;
        }
        finally{
            return conexion;
        }
    }
}
