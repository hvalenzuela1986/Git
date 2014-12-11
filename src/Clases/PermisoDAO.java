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
 * @author Catherine
 */
public class PermisoDAO {
    
    public static int Codigo(){
        int codigo = 1;
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select codigo from permisos order by codigo desc limit 1");
            ResultSet rs = consulta.executeQuery();
            if(rs.next()){
                codigo = rs.getInt("codigo") + 1;
            }
        } catch (SQLException ex) {
            
        }
        return codigo;
    }

    public static boolean Guardar(Permiso nuevo){
        try {
            String insert = "insert into permisos values(?,?,?,?,?,?,?,?,?)"; //se guarda en el string la cadena sql que se saca de la 
            PreparedStatement consulta = miConexion.prepareStatement(insert);// establese si se hara insert o select o algo y se le pasa el string del sql
            consulta.setInt(1, nuevo.getCodigo());
            consulta.setString(2, nuevo.getRutFuncionario());//nuevo es una variable de tipo permido q se declaro en el parentesis arriba y al poner el punto accedo a los get de la clase permiso
            consulta.setString(3, nuevo.getRutUsuario());
            consulta.setInt(4, nuevo.getDias());
            consulta.setString(5, nuevo.getFecha());
            consulta.setString(6, nuevo.getMotivo());
            consulta.setString(7, nuevo.getResolucion());
            consulta.setString(8, nuevo.getObservacion());
            consulta.setInt(9, nuevo.getEstado());
            consulta.execute();
            consulta.close();
            for (int i = 0; i < nuevo.getFechas().size(); i++) {
                insert = "insert into fecha_permisos values(?,?)";
                consulta = miConexion.prepareStatement(insert);
                consulta.setInt(1, nuevo.getCodigo());
                consulta.setString(2, nuevo.getFechas().get(i));
                consulta.execute();
                consulta.close();
            }
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static boolean ActualizarPermiso(Permiso edit){
        try {
            String sql = "update permisos set cant_dias = ?, motivo = ? where codigo = ?";
            PreparedStatement consulta = miConexion.prepareStatement(sql);
            consulta.setInt(1, edit.getDias());
            consulta.setString(2, edit.getMotivo());
            consulta.setInt(3, edit.getCodigo());
            consulta.execute();
            consulta.close();
            sql = "delete from fecha_permisos where codigo_permiso = ?";
            consulta = miConexion.prepareStatement(sql);
            consulta.setInt(1, edit.getCodigo());
            consulta.execute();
            consulta.close();
            for (int i = 0; i < edit.getFechas().size(); i++) {
                sql = "insert into fecha_permisos values(?,?)";
                consulta = miConexion.prepareStatement(sql);
                consulta.setInt(1, edit.getCodigo());
                consulta.setString(2, edit.getFechas().get(i));
                consulta.execute();
                consulta.close();
            }
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
 
    public static boolean EliminarPermiso(int codPermiso){
        try {
            String delete = "update permisos set estado = 0 where codigo = ?";
            PreparedStatement consulta = miConexion.prepareStatement(delete);
            consulta.setInt(1, codPermiso);
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static boolean AprobarPermiso(int codPermiso){
        try {
            String update = "update permisos set resolucion = 'Aprobado' where codigo = ?";
            PreparedStatement consulta = miConexion.prepareStatement(update);
            consulta.setInt(1, codPermiso);
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static boolean RechazarPermiso(int codPermiso){
        try {
            String update = "update permisos set resolucion = 'Rechazado' where codigo = ?";
            PreparedStatement consulta = miConexion.prepareStatement(update);
            consulta.setInt(1, codPermiso);
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public static Permiso BuscarPermniso(int codigo){
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select * from permisos where codigo = ? and estado = 1");
            consulta.setInt(1, codigo);
            ResultSet rs = consulta.executeQuery();
            if(rs.next()){
                String rutFuncionario = rs.getString("rut_funcionario");
                String rutUsuario = rs.getString("rut_usuario");
                int dias = rs.getInt("cant_dias");
                String fecha = rs.getString("fecha");
                String motivo = rs.getString("motivo");
                String resolucion = rs.getString("resolucion");
                String observacion = rs.getString("observacion");
                int estado = rs.getInt("estado");
                Permiso permiso = new Permiso(codigo, rutFuncionario, rutUsuario, dias, fecha, motivo, resolucion, observacion, estado);
                return permiso;
            }
        } catch (SQLException ex) {
        }
        return null;
    }
    
    public static ArrayList<String> ObtenerFechas(int codigo){
        ArrayList<String> fechas = new ArrayList<>();
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select fecha from fecha_permisos where codigo_permiso = ? order by str_to_date(fecha, '%d-%m-%Y') asc");
            consulta.setInt(1, codigo);
            ResultSet rs = consulta.executeQuery();
            while(rs.next()){
                String fecha = rs.getString("fecha");
                fechas.add(fecha);
            }
            return fechas;
        } catch (SQLException ex) {
        }
        return null;
    }
    
    public static int ObtenerTotal(String rut, String anyo){
        int total = 0;
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select sum(cant_dias) as total from permisos where rut_funcionario = ? and fecha like '%-" + anyo + "' and resolucion in ('Aprobado','Pendiente') and estado = 1");
            consulta.setString(1, rut);
            ResultSet rs = consulta.executeQuery();//ontiene los datos q son resultado de la consulta
            if(rs.next()){//revisa los registos de el sql guardado en rs 
                total = rs.getInt("total");
            }
        } catch (SQLException ex) {
        }
        return total;
    }
        
    public static ArrayList<Permiso> ObtenerPermisos(){
        ArrayList<Permiso> listado = new ArrayList<>();
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select * from permisos where estado = 1");
            ResultSet rs = consulta.executeQuery();//ontiene los datos q son resultado de la consulta
            while(rs.next()){//revisa los registos de el sql guardado en rs 
                int codigo = rs.getInt("codigo");
                String rutFun = rs.getString("rut_funcionario");
                String rutUsu = rs.getString("rut_usuario");
                int dias = rs.getInt("cant_dias");
                String fecha = rs.getString("fecha");
                String motivo = rs.getString("motivo");
                String resolucion = rs.getString("resolucion");
                String observacion = rs.getString("observacion");
                Permiso permiso = new Permiso(codigo, rutFun, rutUsu, dias, fecha, motivo, resolucion, observacion, 1);
                listado.add(permiso);
            }
        } catch (SQLException ex) {
        }
        return listado;
    }
    
    public static ArrayList<Permiso> ObtenerHistorial(){
        ArrayList<Permiso> listado = new ArrayList<>();
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select * from permisos where estado = 1 order by str_to_date(fecha, '%d-%m-%Y') desc");
            ResultSet rs = consulta.executeQuery();//ontiene los datos q son resultado de la consulta
            while(rs.next()){//revisa los registos de el sql guardado en rs 
                int codigo = rs.getInt("codigo");
                String rutFun = rs.getString("rut_funcionario");
                String rutUsu = rs.getString("rut_usuario");
                int dias = rs.getInt("cant_dias");
                String fecha = rs.getString("fecha");
                String motivo = rs.getString("motivo");
                String resolucion = rs.getString("resolucion");
                String observacion = rs.getString("observacion");
                Permiso permiso = new Permiso(codigo, rutFun, rutUsu, dias, fecha, motivo, resolucion, observacion, 1);
                listado.add(permiso);
            }
        } catch (SQLException ex) {
        }
        return listado;
    }
    
    public static ArrayList<Permiso> PermisosFuncionarioAnyo(String rut, String anyo){
        ArrayList<Permiso> listado = new ArrayList<>();
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select * from permisos where rut_funcionario = ? and fecha like '%-" + anyo + "' estado = 1 order by str_to_date(fecha, '%d-%m-%Y') desc");
            consulta.setString(1, rut);
            ResultSet rs = consulta.executeQuery();//ontiene los datos q son resultado de la consulta
            while(rs.next()){//revisa los registos de el sql guardado en rs 
                int codigo = rs.getInt("codigo");
                String rutFun = rs.getString("rut_funcionario");
                String rutUsu = rs.getString("rut_usuario");
                int dias = rs.getInt("cant_dias");
                String fecha = rs.getString("fecha");
                String motivo = rs.getString("motivo");
                String resolucion = rs.getString("resolucion");
                String observacion = rs.getString("observacion");
                Permiso permiso = new Permiso(codigo, rutFun, rutUsu, dias, fecha, motivo, resolucion, observacion, 1);
                listado.add(permiso);
            }
        } catch (SQLException ex) {
        }
        return listado;
    }
    
    public static ArrayList<Permiso> PermisosFuncionario(String rut){
        ArrayList<Permiso> listado = new ArrayList<>();
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select * from permisos where rut_funcionario = ? and estado = 1 order by str_to_date(fecha, '%d-%m-%Y') desc");
            consulta.setString(1, rut);
            ResultSet rs = consulta.executeQuery();//ontiene los datos q son resultado de la consulta
            while(rs.next()){//revisa los registos de el sql guardado en rs 
                int codigo = rs.getInt("codigo");
                String rutFun = rs.getString("rut_funcionario");
                String rutUsu = rs.getString("rut_usuario");
                int dias = rs.getInt("cant_dias");
                String fecha = rs.getString("fecha");
                String motivo = rs.getString("motivo");
                String resolucion = rs.getString("resolucion");
                String observacion = rs.getString("observacion");
                Permiso permiso = new Permiso(codigo, rutFun, rutUsu, dias, fecha, motivo, resolucion, observacion, 1);
                listado.add(permiso);
            }
        } catch (SQLException ex) {
        }
        return listado;
    }
    
    public static ArrayList<Permiso> ObtenerAprobados(){
        ArrayList<Permiso> listado = new ArrayList<>();
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select * from permisos where resolucion = 'Aprobado' and estado = 1");
            ResultSet rs = consulta.executeQuery();//ontiene los datos q son resultado de la consulta
            while(rs.next()){//revisa los registos de el sql guardado en rs 
                int codigo = rs.getInt("codigo");
                String rutFun = rs.getString("rut_funcionario");
                String rutUsu = rs.getString("rut_usuario");
                int dias = rs.getInt("cant_dias");
                String fecha = rs.getString("fecha");
                String motivo = rs.getString("motivo");
                String resolucion = rs.getString("resolucion");
                String observacion = rs.getString("observacion");
                Permiso permiso = new Permiso(codigo, rutFun, rutUsu, dias, fecha, motivo, resolucion, observacion, 1);
                listado.add(permiso);
            }
        } catch (SQLException ex) {
        }
        return listado;
    }
    
    public static ArrayList<Permiso> ObtenerPendientes(){
        ArrayList<Permiso> listado = new ArrayList<>();
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select * from permisos where resolucion = 'Pendiente' and estado = 1");
            ResultSet rs = consulta.executeQuery();//ontiene los datos q son resultado de la consulta
            while(rs.next()){//revisa los registos de el sql guardado en rs 
                int codigo = rs.getInt("codigo");
                String rutFun = rs.getString("rut_funcionario");
                String rutUsu = rs.getString("rut_usuario");
                int dias = rs.getInt("cant_dias");
                String fecha = rs.getString("fecha");
                String motivo = rs.getString("motivo");
                String resolucion = rs.getString("resolucion");
                String observacion = rs.getString("observacion");
                Permiso permiso = new Permiso(codigo, rutFun, rutUsu, dias, fecha, motivo, resolucion, observacion, 1);
                listado.add(permiso);
            }
        } catch (SQLException ex) {
        }
        return listado;
    }
    
    public static ArrayList<Permiso> ObtenerRechazados(){
        ArrayList<Permiso> listado = new ArrayList<>();
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select * from permisos where resolucion = 'Rechazado' and estado = 1");
            ResultSet rs = consulta.executeQuery();//ontiene los datos q son resultado de la consulta
            while(rs.next()){//revisa los registos de el sql guardado en rs 
                int codigo = rs.getInt("codigo");
                String rutFun = rs.getString("rut_funcionario");
                String rutUsu = rs.getString("rut_usuario");
                int dias = rs.getInt("cant_dias");
                String fecha = rs.getString("fecha");
                String motivo = rs.getString("motivo");
                String resolucion = rs.getString("resolucion");
                String observacion = rs.getString("observacion");
                Permiso permiso = new Permiso(codigo, rutFun, rutUsu, dias, fecha, motivo, resolucion, observacion, 1);
                listado.add(permiso);
            }
        } catch (SQLException ex) {
        }
        return listado;
    }
 
}