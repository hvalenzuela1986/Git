/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author Catherine
 */
public class Permiso {
    private int codigo;
    private String rutFuncionario;
    private String rutUsuario;
    private int dias;
    private String fecha;
    private String motivo;
    private String resolucion;
    private String observacion;
    private int estado;
    private ArrayList<String> fechas;

    public Permiso() {
    }

    public Permiso(int codigo, String rutFuncionario, String rutUsuario, int dias, String motivo, ArrayList<String> fechas) {
        this.codigo = codigo;
        this.rutFuncionario = rutFuncionario;
        this.rutUsuario = rutUsuario;
        this.dias = dias;
        this.motivo = motivo;
        this.fechas = fechas;
    }

    public Permiso(int codigo, String rutFuncionario, String rutUsuario, int dias, String fecha, String motivo, String resolucion, String observacion, int estado) {
        this.codigo = codigo;
        this.rutFuncionario = rutFuncionario;
        this.rutUsuario = rutUsuario;
        this.dias = dias;
        this.fecha = fecha;
        this.motivo = motivo;
        this.resolucion = resolucion;
        this.observacion = observacion;
        this.estado = estado;
    }
    
    public Permiso(int codigo, String rutFuncionario, String rutUsuario, int dias, String motivo, String resolucion, String observacion, int estado) {
        this.codigo = codigo;
        this.rutFuncionario = rutFuncionario;
        this.rutUsuario = rutUsuario;
        this.dias = dias;
        this.motivo = motivo;
        this.resolucion = resolucion;
        this.observacion = observacion;
        this.estado = estado;
    }

    public Permiso(int codigo, String rutFuncionario, String rutUsuario, int dias, String fecha, String motivo, String resolucion, String observacion, int estado, ArrayList<String> fechas) {
        this.codigo = codigo;
        this.rutFuncionario = rutFuncionario;
        this.rutUsuario = rutUsuario;
        this.dias = dias;
        this.fecha = fecha;
        this.motivo = motivo;
        this.resolucion = resolucion;
        this.observacion = observacion;
        this.estado = estado;
        this.fechas = fechas;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getRutFuncionario() {
        return rutFuncionario;
    }

    public void setRutFuncionario(String rutFuncionario) {
        this.rutFuncionario = rutFuncionario;
    }

    public String getRutUsuario() {
        return rutUsuario;
    }

    public void setRutUsuario(String rutUsuario) {
        this.rutUsuario = rutUsuario;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public ArrayList<String> getFechas() {
        return fechas;
    }

    public void setFechas(ArrayList<String> fechas) {
        this.fechas = fechas;
    }
        
}
