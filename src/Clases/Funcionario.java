/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Optimus
 */
public class Funcionario {
    private String rut;
    private String nombre;
    private String fechaNac;
    private int cargo;
    private String fechaIngreso;
    private int nivel;
    private int estado;

    public Funcionario() {
    }

    public Funcionario(String rut, String nombre, String fechaNac, int cargo, String fechaIngreso, int nivel, int estado) {
        this.rut = rut;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.cargo = cargo;
        this.fechaIngreso = fechaIngreso;
        this.nivel = nivel;
        this.estado = estado;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}
